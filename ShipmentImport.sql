USE [SportsStoreJEE]
GO
CREATE TABLE [dbo].[ImportShipment] (
	ID int IDENTITY(1,1) NOT NULL,
	ImportID int NOT NULL,
	DeliverDate date NOT NULL,
	ShipmentId nvarchar(150),
	ShipmentCompany nvarchar(150),
	ShipmentStatus nvarchar(150) NOT NULL,
	CONSTRAINT PK_ImportShipment PRIMARY KEY (ID),
	CONSTRAINT FK_ShipmentImportedImport FOREIGN KEY (ImportID) REFERENCES [dbo].[Import](ID)
)
GO
CREATE OR ALTER PROC USP_GetImportsInShipment @id int
AS
BEGIN
	SELECT ImportID 
	FROM [ImportShipment]
	Inner JOIN Import ON	Import.ID = ImportShipment.ImportID
	WHERE ImportID = @id
END

GO
CREATE OR ALTER PROC USP_FilterShipment
	@ImportID int null,
	@DeliverDate date NULL,
	@ShipmentId nvarchar(150) NULL,
	@ShipmentCompany nvarchar(150) NULL,
	@ShipmentStatus nvarchar(150) NULL
AS
BEGIN
	SELECT * FROM [ImportShipment]
	WHERE (@ImportID is NULL OR ImportID = @ImportID) 
	and (@ShipmentId is NULL OR ShipmentId = @ShipmentId)
	AND (@ShipmentCompany is NULL OR @ShipmentCompany = '' OR ShipmentCompany LIKE '%' + @ShipmentCompany + '%')
	AND (@ShipmentStatus is NULL OR @ShipmentStatus = '' OR ShipmentStatus LIKE '%' + @ShipmentStatus + '%')
END

GO
-- Insert
CREATE OR ALTER PROC USP_InsertShipment 
	@DeliverDate date NULL,
	@ShipmentStatus nvarchar(150) NULL,
	@ShipmentId nvarchar(150),
	@ShipmentCompany nvarchar(150),
	@ImportID int NULL
AS
BEGIN
	INSERT INTO [dbo].[ImportShipment]( DeliverDate, ShipmentStatus,ImportID, ShipmentCompany, ShipmentId) VALUES
	(@DeliverDate,@ShipmentStatus,@ImportID, @ShipmentCompany, @ShipmentId)
END

GO
---- Update
CREATE OR ALTER PROC USP_UpdateShipment
	@ID int,
	@ImportID int,
	@DeliverDate date,
	@ShipmentId nvarchar(150),
	@ShipmentCompany nvarchar(150),
	@ShipmentStatus nvarchar(150)
AS
BEGIN
	UPDATE [dbo].[ImportShipment] SET DeliverDate=@DeliverDate, ImportID=@ImportID, ShipmentStatus=@ShipmentStatus, 
	ShipmentCompany=@ShipmentCompany,  ShipmentId=@ShipmentId
	WHERE ID = @ID
END

GO

-- Delete
CREATE OR ALTER PROC USP_DeleteShipment
	@ID int
AS
BEGIN
	DELETE FROM [ImportShipment] WHERE ID = @ID
	
END

GO

INSERT INTO ImportShipment(DeliverDate, ShipmentCompany, ShipmentId, ImportID, ShipmentStatus) VALUES ( CAST(N'2020-02-01' AS date), 'A', 'AA', 1, N'Processing')
INSERT INTO ImportShipment (DeliverDate, ShipmentCompany, ShipmentId, ImportID, ShipmentStatus) VALUES ( CAST(N'2020-03-05' AS date), 'B', 'BB', 2, N'Processing')
INSERT INTO ImportShipment (DeliverDate, ShipmentCompany, ShipmentId, ImportID, ShipmentStatus) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'CC', 4, N'Processing')
INSERT INTO ImportShipment (DeliverDate, ShipmentCompany, ShipmentId, ImportID, ShipmentStatus) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'CCE', 5, N'Processing')
INSERT INTO ImportShipment (DeliverDate, ShipmentCompany, ShipmentId, ImportID, ShipmentStatus) VALUES ( CAST(N'2020-6-11' AS date), 'E', 'EE', 7, N'Processing')
INSERT INTO ImportShipment (DeliverDate, ShipmentCompany, ShipmentId, ImportID, ShipmentStatus) VALUES ( CAST(N'2020-6-12' AS date), 'D', 'DD', 8, N'Processing')
INSERT INTO ImportShipment (DeliverDate, ShipmentCompany, ShipmentId, ImportID, ShipmentStatus) VALUES ( CAST(N'2020-6-13' AS date), 'F', 'FF', 9, N'Processing')
