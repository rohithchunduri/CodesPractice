SET @row := 0;
select newOrders.order_id, newOrders.dish_name, newOrders.order_qty
from(
select *, @row := @row + 1 as row
from orders inner join menu using(dish_id)
     inner join customers using(customer_id)
ORDER by @row DESC
) as newOrders
group by newOrders.dish_id , newOrders.order_id
having count(*) > 1
