USE [SportsStoreJEE]
GO

CREATE TABLE [dbo].[OrderShipment] (
	ID int IDENTITY(1,1) NOT NULL,
	OrderID int NOT NULL,
	DeliverDate date NOT NULL,
	ShipmentId nvarchar(150),
	ShipmentCompany nvarchar(150),
	ShipmentStatus nvarchar(150) NOT NULL,
	CONSTRAINT PK_OrderShipment PRIMARY KEY (ID),
	CONSTRAINT FK_ShipmentOrderedOrder FOREIGN KEY (OrderID) REFERENCES [dbo].[Order](ID)
)
GO
--CREATE OR ALTER PROC USP_GetOrdersInShipment @id int
--AS
--BEGIN
--	SELECT OrderID 
--	FROM [OrderShipment]
--	Inner JOIN Order ON	Order.ID = OrderShipment.OrderID
--	WHERE OrderID = @id
--END

GO
CREATE OR ALTER PROC USP_FilterOrderShipment
	@OrderID int NULL,
	@DeliverDate date NULL,
	@ShipmentId nvarchar(150) NULL,
	@ShipmentCompany nvarchar(150) NULL,
	@ShipmentStatus nvarchar(150) NULL
AS
BEGIN
	SELECT * FROM [OrderShipment]
	WHERE (@OrderID is NULL OR OrderID = @OrderID) 
	AND (@DeliverDate is NULL OR DeliverDate = @DeliverDate)
	AND (@ShipmentId is NULL OR ShipmentId = @ShipmentId)
	AND (@ShipmentCompany is NULL OR @ShipmentCompany = '' OR ShipmentCompany LIKE '%' + @ShipmentCompany + '%')
	AND (@ShipmentStatus is NULL OR @ShipmentStatus = '' OR ShipmentStatus LIKE '%' + @ShipmentStatus + '%')
END

GO
-- Insert
CREATE OR ALTER PROC USP_InsertOrderShipment 
	@OrderID int NULL,
	@DeliverDate date NULL,
	@ShipmentStatus nvarchar(150) NULL,
	@ShipmentId nvarchar(150),
	@ShipmentCompany nvarchar(150)
	
AS
BEGIN
	INSERT INTO [dbo].[OrderShipment]( DeliverDate, ShipmentStatus,OrderID, ShipmentCompany, ShipmentId) VALUES
	(@DeliverDate,@ShipmentStatus,@OrderID, @ShipmentCompany, @ShipmentId)
END

GO
---- Update
CREATE OR ALTER PROC USP_UpdateOrderShipment
	@ID int,
	@OrderID int,
	@DeliverDate date,
	@ShipmentStatus nvarchar(150),
	@ShipmentId nvarchar(150),
	@ShipmentCompany nvarchar(150)
	
AS
BEGIN
	UPDATE [dbo].[OrderShipment] SET DeliverDate=@DeliverDate, OrderID=@OrderID, ShipmentStatus=@ShipmentStatus, 
	ShipmentCompany=@ShipmentCompany,  ShipmentId=@ShipmentId
	WHERE ID = @ID
END

GO

-- Delete
CREATE OR ALTER PROC USP_DeleteOrderShipment
	@ID int
AS
BEGIN
	DELETE FROM [OrderShipment] WHERE ID = @ID
	
END

GO

INSERT INTO OrderShipment(DeliverDate, ShipmentCompany, ShipmentId, OrderID, ShipmentStatus) VALUES ( CAST(N'2020-02-01' AS date), 'A', 'AA', 1, N'Processing')
INSERT INTO OrderShipment (DeliverDate, ShipmentCompany, ShipmentId, OrderID, ShipmentStatus) VALUES ( CAST(N'2020-03-05' AS date), 'B', 'BB', 2, N'Processing')
INSERT INTO OrderShipment (DeliverDate, ShipmentCompany, ShipmentId, OrderID, ShipmentStatus) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'CC', 4, N'Processing')
INSERT INTO OrderShipment (DeliverDate, ShipmentCompany, ShipmentId, OrderID, ShipmentStatus) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'CCE', 5, N'Processing')
INSERT INTO OrderShipment (DeliverDate, ShipmentCompany, ShipmentId, OrderID, ShipmentStatus) VALUES ( CAST(N'2020-6-11' AS date), 'E', 'EE', 7, N'Processing')
INSERT INTO OrderShipment (DeliverDate, ShipmentCompany, ShipmentId, OrderID, ShipmentStatus) VALUES ( CAST(N'2020-6-12' AS date), 'D', 'DD', 8, N'Processing')
INSERT INTO OrderShipment (DeliverDate, ShipmentCompany, ShipmentId, OrderID, ShipmentStatus) VALUES ( CAST(N'2020-6-13' AS date), 'F', 'FF', 9, N'Processing')

exec USP_FilterOrderShipment @OrderID=NULL,@DeliverDate=NULL,@ShipmentStatus='Processing',@ShipmentID=NULL,@ShipmentCompany=NULL