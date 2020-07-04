mvn install
asadmin
delete-domain sportstore
create-domain --adminport 5000 --instanceport 9000 --profile developer --user admin sportstore
start-domain sportstore
deploy --port 5000 --host localhost "D:\University\Courses\J2EE\New MCV/SportsStore_JEE/target/SportsStore-1.0.war"