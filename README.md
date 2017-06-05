##About

#####Build and deploy
1. Run `bash -x buildDockerDev.sh` to create a fat jar and rebuild docker image with it
2. Run `docker-compose up -d` to start app and db
3. Test db by running :

#####Test DB from docker container.
1. SSH to docker instance: `sudo docker exec -i -t database.dev /bin/bash`
2. Connect as postgres: psql -U postgres
3. List databases `\l` or tables `\d` (`\du`, `\dt`)

#####Test DB from host machine.

1. Connect to postgres from host machine ` psql -h localhost (or -h 127.0.0.1) -U postgres -d laboratory` and 
type in the password
2. Check tables.

#####Test app from host machine.

`curl localhost:8080/hello` command should return json file.

#### To start both manually

DB: `docker run --name database.dev -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:9.6.3`
then ssh `docker exec -it`
the connect to PG `psql -U postgres`
run `create database laboratory`
now can run migration `sbt flywayMigrate`


App:`docker run -d -p 8080:8080 akka_app`

 
### Application server

Application have to listen on `0.0.0.0` address to accept all external connections.
If instead it listens on `localhost` then akka server will be able to receive only connections
within docker container.

### Docker

List of usefull commands:

- docker run -d -p 8080:8080 akka_app
- docker run -d -p 80:80 nginx:alpine
- docker ext -i -t 9834598437 /bin/bash
- docker ps -a
- docker stop $(docker ps -a -q)
- docker rm $(docker ps -a -q)
- docker inspect conteiner_id //to find out the IP of the container
- docker run -d -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password --name laboratory -p 5432:5432 --restart=always postgres

### Docker compose

- docker-compose up -d
- docker-compose stop
- docker-compose rm -f
- docker-compose ps
- docker-compose ps -q db
- docker-compose rm -v //remove volumes

### Postgres

Postgres configuration file lives in `/var/lib/postgresql/data/postgresql.conf`

To start/stop Postgres on MacOS (if was installed using brew):

- brew services start postgresql
- brew services stop postgresql

psql -U postgres //connect as postgres
psql laboratory dev //connect as dev

Usefull postgres commands:

- \l //list all databases
- \d //list all tables
- \du //see all users

