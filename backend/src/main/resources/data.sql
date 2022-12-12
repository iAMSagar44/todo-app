INSERT
INTO
  users
  (last_name, first_name)
VALUES
  ('Apple', 'John');

  INSERT
INTO
  users
  (last_name, first_name)
VALUES
  ('Smith', 'Steve');

INSERT
INTO
  todo
  (user_id, title, completed)
VALUES
  (1, 'Water plants', FALSE);


 INSERT
 INTO
   todo
   (user_id, title, completed)
 VALUES
   (1, 'Bring flowers', FALSE);

   INSERT
INTO
  todo
  (user_id, title, completed, completed_date_time)
VALUES
  (1, 'go for a run', TRUE, NOW());

     INSERT
  INTO
    todo
    (user_id, title, completed, completed_date_time)
  VALUES
    (1, 'workout', TRUE, NOW());

  INSERT
  INTO
    todo
    (user_id, title, completed)
  VALUES
    (2, 'Finish ToDo app project', FALSE);


   INSERT
   INTO
     todo
     (user_id, title, completed)
   VALUES
     (2, 'Complete course', FALSE);