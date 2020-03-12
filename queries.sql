## Part 1: Test it with SQL  // added per Part1.SQL.2

SHOW FIELDS FROM job;
-- SHOW STATUS LIKE 'conn%';
--SELECT USER();
--SELECT DATABASE();
--SHOW STATUS;
--SHOW PROCESSLIST;
-- SELECT *
-- FROM job;

## Part 2: Test it with SQL
SELECT *
FROM employers
WHERE (location = "Saint Louis");

## Part 3: Test it with SQL
DROP TABLE job;

## Part 4: Test it with SQL
SELECT DISTINCT name, description
FROM skill
INNER JOIN job_skills ON skill.id
ORDER BY name ASC;