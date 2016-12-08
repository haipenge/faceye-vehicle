mongo_bin=/data/tools/mongo/mongodb-linux-x86_64-3.0.7/bin
cd $mongo_bin
./mongodump -h localhost -d faceye_vehicle -o /data/bak/faceye_vehicle/
exit

