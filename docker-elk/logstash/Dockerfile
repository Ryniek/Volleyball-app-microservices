ARG ELASTIC_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/logstash/logstash:${ELASTIC_VERSION}
RUN mkdir /usr/share/api-gateway-logs
RUN mkdir /usr/share/config-server-logs
RUN mkdir /usr/share/eureka-server-logs
RUN mkdir /usr/share/game-service-logs
RUN mkdir /usr/share/player-statistics-service-logs
RUN mkdir /usr/share/team-management-logs
RUN mkdir /usr/share/user-management-logs

# Add your logstash plugins setup here
# Example: RUN logstash-plugin install logstash-filter-json
