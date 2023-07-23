docker run --rm --name jsf_demo_application --network demo_app \
-p 8085:8080 \
-p 9995:9990 \
-d jsf_demo_application:v1