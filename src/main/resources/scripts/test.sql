/*SET autocommit = 0;

START TRANSACTION;


ALTER TABLE user
  ADD admin_id int(11);
ALTER TABLE user
  ADD FOREIGN KEY (admin_id) REFERENCES user(id);


 COMMIT;
**/

insert into user values (null,'test user', MD5('12345678a'),now(),now(),1);
