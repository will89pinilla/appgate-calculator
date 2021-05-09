FROM adoptopenjdk/openjdk11 as build

WORKDIR /workspace/app

COPY gradlew .
COPY .gradle .gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradle gradle/
COPY src src/

RUN ./gradlew clean build -x test
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM adoptopenjdk/openjdk11
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
RUN mkdir -p ${DEPENDENCY}
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.appgate.calculator.CalculatorApplication"]