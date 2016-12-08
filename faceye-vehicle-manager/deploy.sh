ROOT=$(dirname $(cd "$(dirname "$0")";pwd))
DEPLOY_PATH='/data/deploy/faceye-vehicle-manager'
RESIN_HOME='/data/tools/resin/resin-vehicle-manager'
cd $ROOT
git pull
cd $ROOT/faceye-vehicle-entity
mvn clean compile package install -D maven.test.skip=true -P product
cd $ROOT/faceye-vehicle-manager
mvn clean compile war:war -D maven.test.skip=true  -P product
sh $RESIN_HOME/bin/resin.sh stop
sleep 5
rm -rf $DEPLOY_PATH/faceye-vehicle-manager.war
cp $ROOT/faceye-vehicle-manager/target/faceye-vehicle-manager.war $DEPLOY_PATH
$RESIN_HOME/bin/resin.sh start
