FROM adoptopenjdk/openjdk11 as build

#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
WORKDIR /workspace/app

COPY gradlew .
COPY .gradle .gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradle gradle/
COPY src src/
RUN ls -la .

RUN ./gradlew clean build
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM adoptopenjdk/openjdk11
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.appgate.calculator.CalculatorApplication"]
