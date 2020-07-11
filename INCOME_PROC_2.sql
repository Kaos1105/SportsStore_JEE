--Tổng Doanh thu
CREATE or alter PROCEDURE USP_IncomeStatistic(@begin Date,
    @end Date,  @id int)
AS
BEGIN
select TotalIncome.*, ISNULL(ProductIncome.p_ImportQuantity,0) as p_ImportQuantity ,
	ISNULL(ProductIncome.p_OrderQuantity,0) as p_OrderQuantity,
	ISNULL(ProductIncome.ProductIncome,0) as  p_Income,
	ISNULL(ProductIncome.ProductCost,0) as  p_Cost
	from(   select Year(PlacementDate) as YEAR,
        Month(PlacementDate) as MONTH,
		SUM(ImportQuantity) as ImportQuantity, 
		SUM(OrderQuantity) as OrderQuantity,
        SUM(OrderQuantity * OrderPrice) as TotalIncome,SUM(ImportQuantity * ImportPrice) as TotalCost
    from (
        select ISNULL(o.PlacementDate, i.PlacementDate) as PlacementDate,
            ISNULL(op.Quantity, 0) as OrderQuantity,
            ISNULL(p.Price, 0) as OrderPrice,
            ISNULL(ip.Quantity, 0) as ImportQuantity,
            ISNULL(p2.ImportPrice, 0) as ImportPrice
        from [Order] o
            inner join [OrderedProduct] op on op.OrderID = o.ID
            inner join [Product] p on op.ProductID = p.ID
			and o.status = 'Finished'
            FULL OUTER JOIN [Import] i
            inner join [ImportedProduct] ip on ip.ImportID = i.ID
            inner join [Product] p2 on p2.ID = ip.ProductID 
			and i.status = 'Finished'
			on Month(o.PlacementDate) = Month(i.PlacementDate)  
			and Year(o.PlacementDate) = Year(i.PlacementDate)
            
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)) as TotalIncome full outer join (select Year(PlacementDate) as p_YEAR,
        Month(PlacementDate) as p_MONTH,
		SUM(ImportQuantity) as p_ImportQuantity, 
		SUM(OrderQuantity) as p_OrderQuantity,
        SUM(OrderQuantity * OrderPrice) as ProductIncome,SUM(ImportQuantity * ImportPrice) as ProductCost
    from (
        select ISNULL(o.PlacementDate, i.PlacementDate) as PlacementDate,
            ISNULL(op.Quantity, 0) as OrderQuantity,
            ISNULL(p.Price, 0) as OrderPrice,
            ISNULL(ip.Quantity, 0) as ImportQuantity,
            ISNULL(p2.ImportPrice, 0) as ImportPrice
        from [Order] o
            inner join [OrderedProduct] op on op.OrderID = o.ID
            inner join [Product] p on op.ProductID = p.ID 
			and o.status = 'Finished' and p.ID=@id
            FULL OUTER JOIN [Import] i
            inner join [ImportedProduct] ip on ip.ImportID = i.ID
            inner join [Product] p2 on p2.ID = ip.ProductID 
			and i.status = 'Finished'  and p2.ID=@id
			on Month(o.PlacementDate) = Month(i.PlacementDate)
			and Year(o.PlacementDate) = Year(i.PlacementDate)
           
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)) as ProductIncome on TotalIncome.MONTH=ProductIncome.p_MONTH and TotalIncome.YEAR=ProductIncome.p_YEAR order by YEAR
END
 GO

  EXEC USP_IncomeStatistic @begin =N'2020-9-1', @end = N'2021-8-8', @id=0

 --Doanh thu theo sản phẩm
 CREATE or alter PROCEDURE USP_ProductIncomeStatistic(@begin Date,
    @end Date, @id int)
AS
BEGIN
    select Year(PlacementDate) as YEAR,
        Month(PlacementDate) as MONTH,
		SUM(ImportQuantity) as ImportQuantity, 
		SUM(OrderQuantity) as OrderQuantity,
        SUM(OrderQuantity * OrderPrice) -SUM(ImportQuantity * ImportPrice) as INCOME
    from (
        select ISNULL(o.PlacementDate, i.PlacementDate) as PlacementDate,
            ISNULL(op.Quantity, 0) as OrderQuantity,
            ISNULL(p.Price, 0) as OrderPrice,
            ISNULL(ip.Quantity, 0) as ImportQuantity,
            ISNULL(p2.ImportPrice, 0) as ImportPrice
        from [Order] o
            inner join [OrderedProduct] op on op.OrderID = o.ID
            inner join [Product] p on op.ProductID = p.ID
			and o.status = 'Finished'
            FULL OUTER JOIN [Import] i
            inner join [ImportedProduct] ip on ip.ImportID = i.ID
            inner join [Product] p2 on p2.ID = ip.ProductID 
			and i.status = 'Finished'
			on Month(o.PlacementDate) = Month(i.PlacementDate)
			and Year(o.PlacementDate) = Year(i.PlacementDate) 
			and p.ID=@id and p2.ID=@id
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)
END
 GO

 EXEC USP_ProductIncomeStatistic @begin =N'2020-9-1', @end = N'2021-8-8', @id=3

 -- Tổng Doanh số
CREATE or alter PROCEDURE USP_RevenueStatistic(@begin Date,
    @end Date, @id int)
AS
BEGIN
select TotalRevenue.*, ISNULL(ProductRevenue.p_REVENUE,0) as p_REVENUE ,
 ISNULL(ProductRevenue.p_QUANTITY,0) as p_QUANTITY
	  from(
select Year(PlacementDate) as YEAR,
        Month(PlacementDate) as MONTH,
        SUM(OrderQuantity * OrderPrice) - SUM(OrderQuantity * ImportPrice) as REVENUE,
		SUM(OrderQuantity) as QUANTITY
    from (
        select o.PlacementDate as PlacementDate,
            ISNULL(op.Quantity, 0) as OrderQuantity,
            ISNULL(p.Price, 0) as OrderPrice, 
            ISNULL(p.ImportPrice, 0) as ImportPrice
        from [Order] o
            inner join [OrderedProduct] op on op.OrderID = o.ID
            inner join [Product] p on op.ProductID = p.ID
            and o.status = 'Finished' 
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)
) as TotalRevenue full outer join (  select 
	Year(PlacementDate) as p_YEAR,
        Month(PlacementDate) as p_MONTH,
        SUM(OrderQuantity * OrderPrice) - SUM(OrderQuantity * ImportPrice) as p_REVENUE,
		SUM(OrderQuantity) as p_QUANTITY
    from (
        select o.PlacementDate as PlacementDate,
            ISNULL(op.Quantity, 0) as OrderQuantity,
            ISNULL(p.Price, 0) as OrderPrice, 
            ISNULL(p.ImportPrice, 0) as ImportPrice
        from [Order] o
            inner join [OrderedProduct] op on op.OrderID = o.ID
            inner join [Product] p on op.ProductID = p.ID 
            and o.status = 'Finished'  AND  p.ID = @ID
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)) as ProductRevenue on ProductRevenue.p_MONTH=TotalRevenue.MONTH and ProductRevenue.p_YEAR=TotalRevenue.year
	order by Year
    
END
 GO

 EXEC USP_RevenueStatistic @begin =N'2020-1-1', @end = N'2021-9-7', @id=3



--Doanh thu năm
--CREATE or alter PROCEDURE USP_YearlyIncomeStatistic(@begin Date,
--    @end Date)
--AS
--BEGIN
--    select Year(PlacementDate) as YEAR,
--        SUM(OrderQuantity * OrderPrice) - SUM(ImportQuantity * ImportPrice) as INCOME
--    from (
--        select ISNULL(o.PlacementDate, i.PlacementDate) as PlacementDate,
--            ISNULL(op.Quantity, 0) as OrderQuantity,
--            ISNULL(p.Price, 0) as OrderPrice,
--            ISNULL(ip.Quantity, 0) as ImportQuantity,
--            ISNULL(p2.ImportPrice, 0) as ImportPrice
--        from [Order] o
--            inner join [OrderedProduct] op on op.OrderID = o.ID
--            inner join [Product] p on op.ProductID = p.ID
--            FULL OUTER JOIN [Import] i
--            inner join [ImportedProduct] ip on ip.ImportID = i.ID
--            inner join [Product] p2 on p2.ID = ip.ProductID on o.PlacementDate = i.PlacementDate
--            where o.status = 'Finished' and i.status = 'Finished'
--    ) as t
--    where PlacementDate >= @begin
--        AND PlacementDate <= @end
--    group by YEAR(PlacementDate)
--END
--GO

--  EXEC USP_YearlyIncomeStatistic @begin =N'2020-1-1', @end = N'2021-7-7'

  --Doanh số năm
--CREATE or alter PROCEDURE USP_YearlyRevenuetatistic(@begin Date,
--    @end Date)
--AS
--BEGIN
--    select Year(PlacementDate) as YEAR,
--        SUM(OrderQuantity * OrderPrice) - SUM(OrderQuantity * ImportPrice) as REVENUE,
--		SUM(OrderQuantity) as QUANTITY
--    from (
--        select o.PlacementDate as PlacementDate,
--            ISNULL(op.Quantity, 0) as OrderQuantity,
--            ISNULL(p.Price, 0) as OrderPrice, 
--            ISNULL(p.ImportPrice, 0) as ImportPrice
--        from [Order] o
--            inner join [OrderedProduct] op on op.OrderID = o.ID
--            inner join [Product] p on op.ProductID = p.ID
--            where o.status = 'Finished' 
--    ) as t
--    where PlacementDate >= @begin
--        AND PlacementDate <= @end
--    group by YEAR(PlacementDate)
--END
-- GO
-- EXEC USP_YearlyRevenuetatistic @begin =N'2020-1-1', @end = N'2020-7-7'

--Doanh số theo sản phẩm
 CREATE or alter PROCEDURE USP_ProductRevenueStatistic(@begin Date,@end Date, @ID int)
AS
BEGIN
    select 
	Year(PlacementDate) as YEAR,
        Month(PlacementDate) as MONTH,
        SUM(OrderQuantity * OrderPrice) - SUM(OrderQuantity * ImportPrice) as REVENUE,
		SUM(OrderQuantity) as QUANTITY
    from (
        select o.PlacementDate as PlacementDate,
            ISNULL(op.Quantity, 0) as OrderQuantity,
            ISNULL(p.Price, 0) as OrderPrice, 
            ISNULL(p.ImportPrice, 0) as ImportPrice
        from [Order] o
            inner join [OrderedProduct] op on op.OrderID = o.ID
            inner join [Product] p on op.ProductID = p.ID 
            where o.status = 'Finished'  AND  p.ID = @ID
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)
END
 GO
  EXEC USP_ProductRevenueStatistic @begin =N'2020-1-1', @end = N'2021-7-7', @id=2


 --Doanh số sản phẩm theo năm
--CREATE or alter PROCEDURE USP_ProductYearlyRevenueStatistic(@begin Date,@end Date, @ID int)
--AS
--BEGIN
--      select Year(PlacementDate) as YEAR, 
--        SUM(OrderQuantity * OrderPrice)- SUM(OrderQuantity * ImportPrice) as REVENUE, 
--		SUM(OrderQuantity) as QUANTITY
--    from (
--        select o.PlacementDate as PlacementDate,
--            ISNULL(op.Quantity, 0) as OrderQuantity,
--            ISNULL(p.Price, 0) as OrderPrice, 
--            ISNULL(p.ImportPrice, 0) as ImportPrice
--        from [Order] o
--            inner join [OrderedProduct] op on op.OrderID = o.ID
--            inner join [Product] p on op.ProductID = p.ID 
--            where   o.status = 'Finished' AND   p.ID = @ID
--    ) as t
--    where PlacementDate >= @begin
--        AND PlacementDate <= @end
--    group by YEAR(PlacementDate)
--END
--GO

--Tính hàng tồn theo sản phẩm
--CREATE or alter PROCEDURE USP_CALCULATESTOCK(@begin Date,@end Date,@ID int)
--AS 
--BEGIN
-- select  Year(PlacementDate) as YEAR,
--        Month(PlacementDate) as MONTH, 
--		SUM(ImportQuantity) as ImportQuantity, 
--		SUM(OrderQuantity) as OrderQuantity from
--   (
--	select  ISNULL(o.PlacementDate, i.PlacementDate) as PlacementDate,
--            ISNULL(op.Quantity, 0) as OrderQuantity, 
--            ISNULL(ip.Quantity, 0) as ImportQuantity
--        from [Order] o
--			inner join [OrderedProduct] op on op.OrderID = o.ID 
--            FULL OUTER JOIN [Import] i
--            inner join [ImportedProduct] ip on ip.ImportID = i.ID
--			on op.ProductID = ip.ProductID 
--			on Month(o.PlacementDate) = Month(i.PlacementDate)
--            where o.status = 'Finished' AND i.status = 'Finished' AND (op.ProductID = @ID OR ip.ProductID = @ID) 
--			and Year(o.PlacementDate) = Year(i.PlacementDate) 
--			) as t 
--	where PlacementDate >= @begin
--        AND PlacementDate <= @end
--    group by YEAR(PlacementDate),
--    Month(PlacementDate)
--END
--GO

--EXEC USP_CALCULATESTOCK   @begin =N'2020-1-1', @end = N'2021-7-7',@ID=2
