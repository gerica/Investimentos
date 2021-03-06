<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<contextName>Investimentos</contextName>
	<appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
		<layout class="ch.qos.logback.classic.PatternLayout">
			<Pattern>%d{HH:mm:ss.SSS} [%thread] %contextName %-5level %logger{36} - %msg%n
			</Pattern>
		</layout>
	</appender>

	<appender name="FILE" class="ch.qos.logback.core.FileAppender">
		<file>investimento.log</file>
		<encoder>
			<pattern>%date %level [%thread] %logger{10} [%file:%line] %msg%n
			</pattern>
		</encoder>
	</appender>

	<logger name="br.cs" level="INFO" />

	<root level="OFF">
		<appender-ref ref="FILE" />
		<appender-ref ref="STDOUT" />
	</root>
</configuration>