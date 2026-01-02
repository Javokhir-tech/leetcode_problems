# Write your MySQL query statement below
with student_subjects as (
    select 
        s.student_id, 
        s.student_name,
        sub.subject_name
    from
        students s,
        subjects sub
)
select
    s.student_id, 
    s.student_name,
    s.subject_name,
    count(e.subject_name) as attended_exams
from 
    student_subjects s
left join
    examinations e
using (student_id, subject_name)
group by student_id, subject_name
order by student_id, subject_name
