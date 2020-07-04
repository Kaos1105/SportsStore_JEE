



CREATE PROCEDURE USP_OrderStatistics
AS
BEGIN
    select PlacementDate, Count(*)
    from [Order]
    group by (PlacementDate)
END;

exec USP_OrderStatistics
GO;
CREATE PROCEDURE USP_ProductStatistics
AS
BEGIN
    select count(*)
    from [Product]
    where Stock > 0
END;

exec USP_ProductStatistics
 