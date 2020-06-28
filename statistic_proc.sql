



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
select YEAR(PlacementDate) as YEAR, MONTH(PlacementDate) as MONTH, sum(price*quantity) - sum(ImportPrice*Quantity) AS INCOME from [Order] o
inner join [OrderedProduct] op
on o.ID = op.OrderID
inner join [Product] p
on op.ProductID = p.ID
WHERE (PlacementDate >= @begin AND PlacementDate <= @end)
group by YEAR(PlacementDate),MONTH(PlacementDate)
END;

exec USP_MonthlyIncomeStatistic '20200101','20201222'


CREATE PROCEDURE USP_YearlyIncomeStatistic(@begin Date,@end Date)
AS
BEGIN
select YEAR(PlacementDate) as YEAR,  sum(price*quantity) - sum(ImportPrice*Quantity) AS INCOME from [Order] o
inner join [OrderedProduct] op
on o.ID = op.OrderID
inner join [Product] p
on op.ProductID = p.ID
WHERE (PlacementDate >= @begin AND PlacementDate <= @end)
group by YEAR(PlacementDate) 

END;

exec USP_YearlyIncomeStatistic '20200101','20201222'
  