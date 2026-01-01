# Write your MySQL query statement below
with complete_process_time as (
    select 
        a1.machine_id,
        case 
            when 
                a2.activity_type = 'end' and a1.activity_type = 'start' 
            then 
                (a2.timestamp - a1.timestamp) 
            end as total_processing_time
    from 
        activity a1
    join 
        activity a2
    on a1.machine_id = a2.machine_id
    where 
        a1.process_id = a2.process_id and a2.activity_type != a1.activity_type
)
select 
    machine_id,
    round(avg(total_processing_time), 3) as processing_time
from 
    complete_process_time
where
    total_processing_time is not null
group by machine_id
