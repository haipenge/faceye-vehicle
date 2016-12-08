ROOT=$(dirname $(cd "$(dirname "$0")";pwd))
RESIN_HOME='/app/resin/resin-search-manager'
DEPLOY_PATH='/data/deploy/faceye-vehicle'
cd $ROOT
git pull
cd $ROOT/faceye-vehicle-entity
mvn clean compile package install -D maven.test.skip=true -P product
cd $ROOT/faceye-vehicle-mobile
mvn clean compile war:war -D maven.test.skip=true  -P product
sh $RESIN_HOME/bin/resin.sh stop
sleep 5
rm -rf $DEPLOY_PATH/faceye-vehicle-mobile.war
cp $ROOT/faceye-vehicle-mobile/target/faceye-vehicle-mobile.war $DEPLOY_PATH
$RESIN_HOME/bin/resin.sh start
