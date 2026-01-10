# Write your MySQL query statement below
with first_orders as (
    select
        order_date,
        customer_pref_delivery_date,
        row_number() over (
            partition by customer_id 
            order by order_date asc
        ) as row_num
    from
        delivery
)
select
    round(
        sum(case when order_date = customer_pref_delivery_date then 1 else 0 end) * 100 / count(*), 2
    ) as immediate_percentage
from
    first_orders 
where row_num = 1

