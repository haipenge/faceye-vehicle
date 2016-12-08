echo 'start...'
if [ $1 == "product" ];then
mvn clean compile jetty:run -D maven.test.skip=true -D jetty.port=9091 -P product
else
mvn clean compile jetty:run -D maven.test.skip=true
fi
