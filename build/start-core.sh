#!/bin/sh

command=$1
cmdname=$(basename $0)

down() {
  echo 'Stoping core'
  mvn -f ../core/pom.xml spring-boot:stop -Dspring-boot.stop.fork

}

up() {
  echo 'running core'
  unset check
  trivia="trivia"

  while [ "$check" != "$trivia" ]
  do
   check=$(docker exec -it  trivia-db  mysql -u root -ptrivia  --batch --skip-column-names -e "SHOW DATABASES LIKE 'trivia'" | tr -d '\r')
   echo "$check"
   sleep 5s
  done


  echo 'running core'
  mvn -f ../core/pom.xml spring-boot:start
}

usage() {
    cat << USAGE >&2

    Tool to manage the core module

    Usage

        $cmdname <command>

    Commands

        up                    Start the core module
        down                  Stop the core module

USAGE
exit 1
}


# checking for commands
case "$command" in
    up)
      up
    ;;
    down)
      down
    ;;
    *)
      usage
    ;;
esac






