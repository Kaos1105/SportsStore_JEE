USE [SportsStoreJEE]
GO
CREATE TABLE [dbo].[Import] (
	ID int IDENTITY(1,1) NOT NULL,
	PlacementDate date NOT NULL,
	WholesalerName nvarchar(50) NOT NULL,
	WholesalerAddress nvarchar(150) NOT NULL,
	WholesalerPhone nvarchar(20) NOT NULL,
	CONSTRAINT PK_Import PRIMARY KEY (ID),
)
GO
CREATE TABLE [dbo].[ImportedProduct] (
	ProductID int NOT NULL,
	ImportID int NOT NULL,
	Quantity int NOT NULL,

	CONSTRAINT PK_ImportedProduct PRIMARY KEY (ProductID, ImportID),
	CONSTRAINT FK_ProductImportedProduct FOREIGN KEY (ProductID) REFERENCES Product(ID),
	CONSTRAINT FK_ImportImportedProduct FOREIGN KEY (ImportID) REFERENCES [dbo].[Import](ID)
)
GO
INSERT INTO [Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES ( CAST(N'2020-02-01' AS date), 'A', 'A', '123456')
INSERT INTO [Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES ( CAST(N'2020-03-05' AS date), 'B', 'B', '125125')
INSERT INTO [Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'C', '6363')
INSERT INTO [Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES ( CAST(N'2020-12-11' AS date), 'C', 'C', '1122')
INSERT INTO [Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES ( CAST(N'2020-6-11' AS date), 'E', 'E', '2244')
INSERT INTO [Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES ( CAST(N'2020-6-12' AS date), 'D', 'D', '3344')
INSERT INTO [Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES ( CAST(N'2020-6-13' AS date), 'F', 'F', '5566')


INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (1, 2, 50)
INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (1, 5, 30)
INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (2, 3, 10)
INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (3, 1, 150)

INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (2, 4, 100)
INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (3, 5, 150)
INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (4, 6, 200)
INSERT INTO [ImportedProduct] (ImportID, ProductID, Quantity) VALUES (5, 7, 250)

-- Get
GO
CREATE OR ALTER PROC USP_GetProductsInImport @id int
AS
BEGIN
	SELECT Product.*, [ImportedProduct].ImportID, [ImportedProduct].Quantity 
	FROM [ImportedProduct]
	LEFT JOIN Product ON [ImportedProduct].ProductID = Product.ID
	WHERE ImportID = @id
END

GO
CREATE OR ALTER PROC USP_FilterImport
	@PlacementDate date NULL,
	@WholesalerName nvarchar(50) NULL,
	@WholesalerAddress nvarchar(150) NULL,
	@WholesalerPhone nvarchar(20) NULL
AS
BEGIN
	SELECT * FROM [Import]
	WHERE (@PlacementDate is NULL OR PlacementDate = @PlacementDate)
		AND (@WholesalerName is NULL OR @WholesalerName = '' OR WholesalerName LIKE '%' + @WholesalerName + '%')
		AND (@WholesalerAddress is NULL OR @WholesalerAddress = '' OR WholesalerAddress LIKE '%' + @WholesalerAddress + '%')
		AND (@WholesalerPhone is NULL OR @WholesalerPhone = '' OR WholesalerPhone LIKE '%' + @WholesalerPhone + '%')
END

-- Insert
GO
CREATE OR ALTER PROC USP_InsertImport 
	@PlacementDate date,
	@WholesalerName nvarchar(50),
	@WholesalerAddress nvarchar(150),
	@WholesalerPhone nvarchar(20)
AS
BEGIN
	INSERT INTO [dbo].[Import] (PlacementDate, WholesalerName, WholesalerAddress, WholesalerPhone) VALUES (@PlacementDate, @WholesalerName, @WholesalerAddress, @WholesalerPhone)
	SELECT @@IDENTITY AS ID
END

GO
CREATE OR ALTER PROC USP_InsertImportedProduct 
	@ImportID int,
	@ProductID int,
	@Quantity int
AS
BEGIN
	INSERT INTO [dbo].[ImportedProduct] (ImportID, ProductID, Quantity) VALUES (@ImportID, @ProductID, @Quantity)
END

-- Update
GO
CREATE OR ALTER PROC USP_UpdateImport
	@ID int,
	@PlacementDate date,
	@WholesalerName nvarchar(50),
	@WholesalerAddress nvarchar(150),
	@WholesalerPhone nvarchar(20)
AS
BEGIN
	UPDATE [dbo].[Import] SET PlacementDate=@PlacementDate, WholesalerName=@WholesalerName, WholesalerAddress=@WholesalerAddress, WholesalerPhone=@WholesalerPhone WHERE ID = @ID
END

GO
CREATE OR ALTER PROC USP_UpdateImportedProduct
	@ImportID int,
	@ProductID int,
	@Quantity int
AS
BEGIN
	UPDATE [ImportedProduct] SET Quantity=@Quantity WHERE ProductID = @ProductID AND ImportID = @ImportID
END

-- Delete
GO
CREATE OR ALTER PROC USP_DeleteImport
	@ID int
AS
BEGIN
	DELETE FROM [ImportedProduct] WHERE ImportID = @ID
	DELETE FROM [Import] WHERE ID = @ID
END

GO
CREATE OR ALTER PROC USP_DeleteImportedProduct
	@ImportID int
AS
BEGIN
	DELETE FROM [ImportedProduct] WHERE ImportID = @ImportID
END