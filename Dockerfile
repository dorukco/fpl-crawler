FROM openjdk:11-jre-slim-stretch
ENV JAVA_TOOL_OPTIONS="-XX:+CompactStrings -XX:-HeapDumpOnOutOfMemoryError -XX:+ExitOnOutOfMemoryError"
COPY target/crawler-*.jar executable.jar
ENTRYPOINT java -jar executable.jar