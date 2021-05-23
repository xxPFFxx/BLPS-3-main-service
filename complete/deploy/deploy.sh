USER=${1:-265096}
PORT=${2:-8137}
CONFIG=helios.properties
if [ -f $CONFIG ]; then
  mvn -f ../pom.xml package
  FILES=(../target/*.jar)
  JAR=$(basename "${FILES[0]}")
  mv ../target/"${JAR}" ./
  echo "java18 -Dspring.config.location=helios.properties -jar ${JAR}" >> start.sh
  scp -P 2222 ./* s"${USER}"@se.ifmo.ru:~/
  rm "$JAR" start.sh
  ssh -p 2222 s"$USER"@se.ifmo.ru -L "$PORT":localPORT:"$PORT"
else
  echo "Create $CONFIG before deploying"
fi