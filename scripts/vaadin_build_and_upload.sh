#!/bin/zsh
mvn clean package -Pproduction
ssh drmullerj@willy72.de -q 'sudo supervisorctl stop spdPG'
scp target/spdpg-1.0-SNAPSHOT.jar drmullerj@willy72.de:/var/www/tecc/spdPG.jar
ssh drmullerj@willy72.de -q 'sudo supervisorctl start spdPG'

