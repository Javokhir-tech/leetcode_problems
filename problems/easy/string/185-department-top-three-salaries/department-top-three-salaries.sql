# Write your MySQL query statement below
with ranked_salaries as (
    select
        e.name as Employee,
        d.name as Department,
        e.salary as Salary,
        dense_rank() over (
            partition by e.departmentId
            order by e.salary desc
        ) as salary_rank
    from
        employee e
    join department d 
    on e.departmentId = d.id
)
select
    Department,
    Employee,
    Salary
from 
    ranked_salaries
where salary_rank <= 3
