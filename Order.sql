USE [SportsStoreJEE]
GO
CREATE TABLE [dbo].[Order] (
	ID int IDENTITY(1,1) NOT NULL,
	PlacementDate date NOT NULL,
	RecipientName nvarchar(50) NOT NULL,
	RecipientAddress nvarchar(150) NOT NULL,
	RecipientPhone nvarchar(20) NOT NULL,
	CONSTRAINT PK_Order PRIMARY KEY (ID),
)
GO
CREATE TABLE [dbo].[OrderedProduct] (
	ProductID int NOT NULL,
	OrderID int NOT NULL,
	Quantity int NOT NULL,
	CONSTRAINT PK_OrderedProduct PRIMARY KEY (ProductID, OrderID),
	CONSTRAINT FK_ProductOrderedProduct FOREIGN KEY (ProductID) REFERENCES Product(ID),
	CONSTRAINT FK_OrderOrderedProduct FOREIGN KEY (OrderID) REFERENCES [dbo].[Order](ID)
)
GO
INSERT INTO [Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES ( CAST(N'2020-02-01' AS date), 'A', 'A', '123456')
INSERT INTO [Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES ( CAST(N'2020-03-05' AS date), 'B', 'B', '125125')
INSERT INTO [Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'C', '6363')
INSERT INTO [Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'C', '1122')
INSERT INTO [Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES ( CAST(N'2020-6-11' AS date), 'E', 'E', '2244')
INSERT INTO [Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES ( CAST(N'2020-6-12' AS date), 'D', 'D', '3344')
INSERT INTO [Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES ( CAST(N'2020-6-13' AS date), 'F', 'F', '5566')


INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (1, 2, 5)
INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (1, 5, 3)
INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (2, 3, 1)
INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (3, 1, 15)

INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (2, 4, 10)
INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (3, 5, 15)
INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (4, 6, 20)
INSERT INTO [OrderedProduct] (OrderID, ProductID, Quantity) VALUES (5, 7, 25)

GO
CREATE OR ALTER PROC USP_GetProductsInOrder @id int
AS
BEGIN
	SELECT Product.*, OrderedProduct.OrderID, OrderedProduct.Quantity 
	FROM OrderedProduct
	LEFT JOIN Product ON OrderedProduct.ProductID = Product.ID
	WHERE OrderID = @id
END

GO
CREATE OR ALTER PROC USP_FilterOrder
AS
BEGIN
	SELECT * FROM [Order]
END

GO
CREATE OR ALTER PROC USP_InsertOrder 
	@PlacementDate date,
	@RecipientName nvarchar(50),
	@RecipientAddress nvarchar(150),
	@RecipientPhone nvarchar(20)
AS
BEGIN
	INSERT INTO [dbo].[Order] (PlacementDate, RecipientName, RecipientAddress, RecipientPhone) VALUES (@PlacementDate, @RecipientName, @RecipientAddress, @RecipientPhone)
	SELECT @@IDENTITY AS ID
END

GO
CREATE OR ALTER PROC USP_InsertOrderedProduct 
	@ProductID int,
	@OrderID int,
	@Quantity int
AS
BEGIN
	INSERT INTO [dbo].[OrderedProduct] (OrderID, ProductID, Quantity) VALUES (@OrderID, @ProductID, @Quantity)
END

GO
CREATE OR ALTER PROC USP_UpdateOrder 
	@ID int,
	@PlacementDate date,
	@RecipientName nvarchar(50),
	@RecipientAddress nvarchar(150),
	@RecipientPhone nvarchar(20)
AS
BEGIN
	UPDATE [Order] SET PlacementDate=@PlacementDate, RecipientName=@RecipientName, RecipientAddress=@RecipientAddress, RecipientPhone=@RecipientPhone WHERE ID = @ID
END

GO
CREATE OR ALTER PROC USP_UpdateOrderedProduct
	@ProductID int,
	@OrderID int,
	@Quantity int
AS
BEGIN
	UPDATE [OrderedProduct] SET Quantity=@Quantity WHERE ProductID = @ProductID AND OrderID = @OrderID
END

GO
CREATE OR ALTER PROC USP_DeleteOrder
	@ID int
AS
BEGIN
	DELETE FROM [OrderedProduct] WHERE OrderID = @ID
	DELETE FROM [Order] WHERE ID = @ID
END
EXEC USP_InsertOrderedProduct 3, 14, 15