SELECT *
FROM student
WHERE age BETWEEN 20 and 24;
SELECT name
FROM student;
SELECT *
FROM student s
WHERE s.name like '%o%';
select *
from student s
where s.age < s.id;
select *
from student s
order by age;