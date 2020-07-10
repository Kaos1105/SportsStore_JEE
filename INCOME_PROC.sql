--Tổng Doanh thu
CREATE or alter PROCEDURE USP_IncomeStatistic(@begin Date,
    @end Date)
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
            FULL OUTER JOIN [Import] i
            inner join [ImportedProduct] ip on ip.ImportID = i.ID
            inner join [Product] p2 on p2.ID = ip.ProductID on Month(o.PlacementDate) = Month(i.PlacementDate)
            where o.status = 'Finished' and i.status = 'Finished' and Year(o.PlacementDate) = Year(i.PlacementDate)
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)
END
 GO

  EXEC USP_IncomeStatistic @begin =N'2020-1-1', @end = N'2021-8-8'

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
            FULL OUTER JOIN [Import] i
            inner join [ImportedProduct] ip on ip.ImportID = i.ID
            inner join [Product] p2 on p2.ID = ip.ProductID on Month(o.PlacementDate) = Month(i.PlacementDate)
            where o.status = 'Finished' and i.status = 'Finished' and Year(o.PlacementDate) = Year(i.PlacementDate) and p.ID=@id
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)
END
 GO

 EXEC USP_ProductIncomeStatistic @begin =N'2020-1-1', @end = N'2021-8-8', @id=2

 -- Tổng Doanh số
CREATE or alter PROCEDURE USP_RevenueStatistic(@begin Date,
    @end Date)
AS
BEGIN
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
            where o.status = 'Finished' 
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)
END
 GO

 EXEC USP_RevenueStatistic @begin =N'2020-1-1', @end = N'2021-7-7'



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
