select DISTINCT customers.customer_name
from customers 
inner join orders Using(customer_id)
where orders.dish_id IN (
Select dish_id 
from orders
group by dish_id
having count(*) = (select MAX(dishIds.dishCount)
from 
(select count(*) as dishCount
from orders
group by dish_id) as dishIds)
)
order by customers.customer_name
