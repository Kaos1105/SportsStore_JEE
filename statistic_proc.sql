



CREATE PROCEDURE USP_OrderStatistics
AS
BEGIN
select PlacementDate,Count(*) from [Order]
group by (PlacementDate)
END;

exec USP_OrderStatistics

CREATE PROCEDURE USP_ProductStatistics
AS
BEGIN
select count(*)   from [Product]
where Stock > 0
END;

exec USP_ProductStatistics

CREATE PROCEDURE USP_MonthlyIncomeStatistic(@begin Date,@end Date)
AS
BEGIN
select sum(price*quantity) - sum(ImportPrice*Quantity) from [Order] o
inner join [OrderedProduct] op
on o.ID = op.OrderID
inner join [Product] p
on op.ProductID = p.ID
WHERE (PlacementDate >= @begin AND PlacementDate <= @end)
END;

exec USP_MonthlyIncomeStatistic '20200101','20201222'


CREATE PROCEDURE USP_YearlyIncomeStatistic(@begin int,@end int)
AS
BEGIN
select sum(price*quantity) - sum(ImportPrice*Quantity) from [Order] o
inner join [OrderedProduct] op
on o.ID = op.OrderID
inner join [Product] p
on op.ProductID = p.ID
WHERE (YEAR(PlacementDate) >= @begin AND YEAR(PlacementDate) <= @end)
END;

exec USP_YearlyIncomeStatistic '2020','2020'