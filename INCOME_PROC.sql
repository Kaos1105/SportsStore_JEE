CREATE PROCEDURE USP_MonthlyIncomeStatistic(@begin Date,
    @end Date)
AS
BEGIN
    select Year(PlacementDate) as YEAR,
        Month(PlacementDate) as MONTH,
        SUM(OrderQuantity * OrderPrice) - SUM(ImportQuantity * ImportPrice) as INCOME
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
            inner join [Product] p2 on p2.ID = ip.ProductID on o.PlacementDate = i.PlacementDate
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate),
    Month(PlacementDate)
END;
 GO;
CREATE PROCEDURE USP_YearlyIncomeStatistic(@begin Date,
    @end Date)
AS
BEGIN
    select Year(PlacementDate) as YEAR,
        SUM(OrderQuantity * OrderPrice) - SUM(ImportQuantity * ImportPrice) as INCOME
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
            inner join [Product] p2 on p2.ID = ip.ProductID on o.PlacementDate = i.PlacementDate
    ) as t
    where PlacementDate >= @begin
        AND PlacementDate <= @end
    group by YEAR(PlacementDate)
END;
