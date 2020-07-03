USE [master]
GO
/****** Object:  Database [SportsStoreJEE]    Script Date: 03-Jul-20 10:45:27 PM ******/
CREATE DATABASE [SportsStoreJEE]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SportsStoreJEE', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\SportsStoreJEE.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SportsStoreJEE_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\SportsStoreJEE_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [SportsStoreJEE] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SportsStoreJEE].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SportsStoreJEE] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET ARITHABORT OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [SportsStoreJEE] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SportsStoreJEE] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SportsStoreJEE] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SportsStoreJEE] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SportsStoreJEE] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SportsStoreJEE] SET  MULTI_USER 
GO
ALTER DATABASE [SportsStoreJEE] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SportsStoreJEE] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SportsStoreJEE] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SportsStoreJEE] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SportsStoreJEE] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SportsStoreJEE', N'ON'
GO
ALTER DATABASE [SportsStoreJEE] SET QUERY_STORE = OFF
GO
USE [SportsStoreJEE]
GO
/****** Object:  Table [dbo].[Import]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Import](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PlacementDate] [date] NOT NULL,
	[WholesalerName] [nvarchar](50) NOT NULL,
	[WholesalerAddress] [nvarchar](150) NOT NULL,
	[WholesalerPhone] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_Import] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ImportedProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImportedProduct](
	[ProductID] [int] NOT NULL,
	[ImportID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
 CONSTRAINT [PK_ImportedProduct] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC,
	[ImportID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ImportShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImportShipment](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ImportID] [int] NOT NULL,
	[DeliverDate] [date] NOT NULL,
	[ShipmentId] [nvarchar](150) NULL,
	[ShipmentCompany] [nvarchar](150) NULL,
	[ShipmentStatus] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_ImportShipment] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PlacementDate] [date] NOT NULL,
	[RecipientName] [nvarchar](50) NOT NULL,
	[RecipientAddress] [nvarchar](150) NOT NULL,
	[RecipientPhone] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderedProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderedProduct](
	[ProductID] [int] NOT NULL,
	[OrderID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
 CONSTRAINT [PK_OrderedProduct] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC,
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderShipment](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NOT NULL,
	[DeliverDate] [date] NOT NULL,
	[ShipmentId] [nvarchar](150) NULL,
	[ShipmentCompany] [nvarchar](150) NULL,
	[ShipmentStatus] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_OrderShipment] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Photo]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Photo](
	[Id] [nvarchar](450) NOT NULL,
	[Url] [nvarchar](max) NULL,
	[IsMain] [bit] NOT NULL,
	[ProductId] [int] NOT NULL,
 CONSTRAINT [PK_Photo] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Brand] [nvarchar](50) NULL,
	[Category] [nvarchar](30) NOT NULL,
	[Price] [decimal](18, 2) NULL,
	[ImportPrice] [bigint] NOT NULL,
	[Stock] [bigint] NOT NULL,
	[DateAdded] [date] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](100) NULL,
	[Password] [nvarchar](100) NOT NULL,
	[IsAdmin] [bit] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0.0)) FOR [ImportPrice]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0)) FOR [Stock]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ('0001-01-01') FOR [DateAdded]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ('FALSE') FOR [IsAdmin]
GO
ALTER TABLE [dbo].[ImportedProduct]  WITH CHECK ADD  CONSTRAINT [FK_ImportImportedProduct] FOREIGN KEY([ImportID])
REFERENCES [dbo].[Import] ([ID])
GO
ALTER TABLE [dbo].[ImportedProduct] CHECK CONSTRAINT [FK_ImportImportedProduct]
GO
ALTER TABLE [dbo].[ImportedProduct]  WITH CHECK ADD  CONSTRAINT [FK_ProductImportedProduct] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[ImportedProduct] CHECK CONSTRAINT [FK_ProductImportedProduct]
GO
ALTER TABLE [dbo].[ImportShipment]  WITH CHECK ADD  CONSTRAINT [FK_ShipmentImportedImport] FOREIGN KEY([ImportID])
REFERENCES [dbo].[Import] ([ID])
GO
ALTER TABLE [dbo].[ImportShipment] CHECK CONSTRAINT [FK_ShipmentImportedImport]
GO
ALTER TABLE [dbo].[OrderedProduct]  WITH CHECK ADD  CONSTRAINT [FK_OrderOrderedProduct] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([ID])
GO
ALTER TABLE [dbo].[OrderedProduct] CHECK CONSTRAINT [FK_OrderOrderedProduct]
GO
ALTER TABLE [dbo].[OrderedProduct]  WITH CHECK ADD  CONSTRAINT [FK_ProductOrderedProduct] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[OrderedProduct] CHECK CONSTRAINT [FK_ProductOrderedProduct]
GO
ALTER TABLE [dbo].[OrderShipment]  WITH CHECK ADD  CONSTRAINT [FK_ShipmentOrderedOrder] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([ID])
GO
ALTER TABLE [dbo].[OrderShipment] CHECK CONSTRAINT [FK_ShipmentOrderedOrder]
GO
ALTER TABLE [dbo].[Photo]  WITH CHECK ADD  CONSTRAINT [FK_Photo_Product_ProductId] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Photo] CHECK CONSTRAINT [FK_Photo_Product_ProductId]
GO
/****** Object:  StoredProcedure [dbo].[USP_DeleteImport]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_DeleteImport]
	@ID int
AS
BEGIN
	DELETE FROM [ImportedProduct] WHERE ImportID = @ID
	DELETE FROM [Import] WHERE ID = @ID
END

GO
/****** Object:  StoredProcedure [dbo].[USP_DeleteImportedProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_DeleteImportedProduct]
	@ImportID int
AS
BEGIN
	DELETE FROM [ImportedProduct] WHERE ImportID = @ImportID
END
GO
/****** Object:  StoredProcedure [dbo].[USP_DeleteOrder]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_DeleteOrder]
	@ID int
AS
BEGIN
	DELETE FROM [OrderedProduct] WHERE OrderID = @ID
	DELETE FROM [Order] WHERE ID = @ID
END
EXEC USP_InsertOrderedProduct 3, 14, 15
GO
/****** Object:  StoredProcedure [dbo].[USP_DeleteOrderShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Delete
CREATE   PROC [dbo].[USP_DeleteOrderShipment]
	@ID int
AS
BEGIN
	DELETE FROM [OrderShipment] WHERE ID = @ID
	
END

GO
/****** Object:  StoredProcedure [dbo].[USP_DeleteShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Delete
CREATE   PROC [dbo].[USP_DeleteShipment]
	@ID int
AS
BEGIN
	DELETE FROM [ImportShipment] WHERE ID = @ID
	
END

GO
/****** Object:  StoredProcedure [dbo].[USP_FilterImport]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_FilterImport]
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
/****** Object:  StoredProcedure [dbo].[USP_FilterOrder]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE     PROC [dbo].[USP_FilterOrder] @name nvarchar(256) NULL,
	@address nvarchar(256) NULL,
	@phone nvarchar(256) NULL,
	@date Date null
AS
BEGIN
	SELECT * FROM [Order] WHERE 
	(@name is null or @name = '' or RecipientName like '%' + @name + '%') and
	(@address is null or @address = '' or RecipientAddress like '%' + @address + '%') and
	(@phone is null or @phone = '' or RecipientPhone like '%' + @phone + '%') and
	(@date is null or PlacementDate = @date)
END
GO
/****** Object:  StoredProcedure [dbo].[USP_FilterOrderShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_FilterOrderShipment]
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
/****** Object:  StoredProcedure [dbo].[USP_FilterProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[USP_FilterProduct]
	@name nvarchar(256) NULL,
	@brand nvarchar(256) NULL,
	@category nvarchar(256) NULL,
	@stock int null
AS
BEGIN
	SELECT * FROM [dbo].Product
	WHERE 
	(@name is null or @name = '' or Name like '%' + @name + '%') and
	(@brand is null or @brand = '' or Brand like '%' + @brand + '%') and
	(@category is null or @category = '' or Category like '%' + @category + '%') and
	(@stock is null or @stock=0 or Stock = @stock)
END
GO
/****** Object:  StoredProcedure [dbo].[USP_FilterShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_FilterShipment]
	@ImportID int NULL,
	@DeliverDate date NULL,
	@ShipmentId nvarchar(150) NULL,
	@ShipmentCompany nvarchar(150) NULL,
	@ShipmentStatus nvarchar(150) NULL
AS
BEGIN
	SELECT * FROM [ImportShipment]
	WHERE (@ImportID is NULL OR ImportID = @ImportID) 
	AND (@DeliverDate is NULL OR DeliverDate = @DeliverDate)
	AND (@ShipmentId is NULL OR ShipmentId = @ShipmentId)
	AND (@ShipmentCompany is NULL OR @ShipmentCompany = '' OR ShipmentCompany LIKE '%' + @ShipmentCompany + '%')
	AND (@ShipmentStatus is NULL OR @ShipmentStatus = '' OR ShipmentStatus LIKE '%' + @ShipmentStatus + '%')
END

GO
/****** Object:  StoredProcedure [dbo].[USP_GetImportsInShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_GetImportsInShipment] @id int
AS
BEGIN
	SELECT ImportID 
	FROM [ImportShipment]
	Inner JOIN Import ON	Import.ID = ImportShipment.ImportID
	WHERE ImportID = @id
END

GO
/****** Object:  StoredProcedure [dbo].[USP_GetProductsInImport]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_GetProductsInImport] @id int
AS
BEGIN
	SELECT Product.*, [ImportedProduct].ImportID, [ImportedProduct].Quantity 
	FROM [ImportedProduct]
	LEFT JOIN Product ON [ImportedProduct].ProductID = Product.ID
	WHERE ImportID = @id
END

GO
/****** Object:  StoredProcedure [dbo].[USP_GetProductsInOrder]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_GetProductsInOrder] @id int
AS
BEGIN
	SELECT Product.*, OrderedProduct.OrderID, OrderedProduct.Quantity 
	FROM OrderedProduct
	LEFT JOIN Product ON OrderedProduct.ProductID = Product.ID
	WHERE OrderID = @id
END


GO
/****** Object:  StoredProcedure [dbo].[USP_InsertImport]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_InsertImport] 
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
/****** Object:  StoredProcedure [dbo].[USP_InsertImportedProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_InsertImportedProduct] 
	@ImportID int,
	@ProductID int,
	@Quantity int
AS
BEGIN
	INSERT INTO [dbo].[ImportedProduct] (ImportID, ProductID, Quantity) VALUES (@ImportID, @ProductID, @Quantity)
END

-- Update
GO
/****** Object:  StoredProcedure [dbo].[USP_InsertOrder]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_InsertOrder] 
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
/****** Object:  StoredProcedure [dbo].[USP_InsertOrderedProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_InsertOrderedProduct] 
	@ProductID int,
	@OrderID int,
	@Quantity int
AS
BEGIN
	INSERT INTO [dbo].[OrderedProduct] (OrderID, ProductID, Quantity) VALUES (@OrderID, @ProductID, @Quantity)
END


GO
/****** Object:  StoredProcedure [dbo].[USP_InsertOrderShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_InsertOrderShipment] 
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
/****** Object:  StoredProcedure [dbo].[USP_InsertPhoto]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 create proc [dbo].[USP_InsertPhoto] 
@id nvarchar(450), @url nvarchar(max), @isMain bit, @productId int   
as
begin
insert into Photo(Id, Url, IsMain, ProductId) values (@id, @url, @isMain, @productId)
end
GO
/****** Object:  StoredProcedure [dbo].[USP_InsertProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 create proc [dbo].[USP_InsertProduct] 
@name nvarchar(100), @brand nvarchar(100), @category nvarchar(100), @price bigint, @importprice bigint, @stock int, @dateAdded Datetime          
as
begin
insert into Product(Name, Brand, Category, Price, ImportPrice, Stock, DateAdded) values (@name, @brand, @category, @price, @importprice, @stock, @dateAdded)
end
GO
/****** Object:  StoredProcedure [dbo].[USP_InsertShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Insert
CREATE   PROC [dbo].[USP_InsertShipment] 
	@ImportID int NULL,
	@DeliverDate date NULL,
	@ShipmentStatus nvarchar(150) NULL,
	@ShipmentId nvarchar(150),
	@ShipmentCompany nvarchar(150)
	
AS
BEGIN
	INSERT INTO [dbo].[ImportShipment]( DeliverDate, ShipmentStatus,ImportID, ShipmentCompany, ShipmentId) VALUES
	(@DeliverDate,@ShipmentStatus,@ImportID, @ShipmentCompany, @ShipmentId)
END

GO
/****** Object:  StoredProcedure [dbo].[USP_InsertUser]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 create proc [dbo].[USP_InsertUser] 
@userName nvarchar(max), @email nvarchar(max), @password nvarchar(max)
as
begin
insert into Users(UserName, Email, Password) values (@userName, @email, @password)
end
GO
/****** Object:  StoredProcedure [dbo].[USP_UpdateImport]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_UpdateImport]
	@ID int,
	@DeliverDate date,
	@ImportID int,
	@ShipmentStatus nvarchar(150)
AS
BEGIN
	UPDATE [dbo].[ImportShipment] SET DeliverDate=@DeliverDate, ImportID=@ImportID, ShipmentStatus=@ShipmentStatus
	WHERE ID = @ID
END
GO
/****** Object:  StoredProcedure [dbo].[USP_UpdateImportedProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_UpdateImportedProduct]
	@ImportID int,
	@ProductID int,
	@Quantity int
AS
BEGIN
	UPDATE [ImportedProduct] SET Quantity=@Quantity WHERE ProductID = @ProductID AND ImportID = @ImportID
END

-- Delete
GO
/****** Object:  StoredProcedure [dbo].[USP_UpdateOrder]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_UpdateOrder] 
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
/****** Object:  StoredProcedure [dbo].[USP_UpdateOrderedProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROC [dbo].[USP_UpdateOrderedProduct]
	@ProductID int,
	@OrderID int,
	@Quantity int
AS
BEGIN
	UPDATE [OrderedProduct] SET Quantity=@Quantity WHERE ProductID = @ProductID AND OrderID = @OrderID
END


GO
/****** Object:  StoredProcedure [dbo].[USP_UpdateOrderShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
---- Update
CREATE   PROC [dbo].[USP_UpdateOrderShipment]
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
/****** Object:  StoredProcedure [dbo].[USP_UpdateProduct]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 create proc [dbo].[USP_UpdateProduct] @id int, 
@name nvarchar(100), @brand nvarchar(100), @category nvarchar(100), @price bigint, @importprice bigint, @stock int, @dateAdded Datetime         
as
begin
update Product set Name=@name, Brand=@brand, Category=@category, Price=@price, ImportPrice=@importprice, Stock=@stock, DateAdded=@dateAdded where Id=@id
end
GO
/****** Object:  StoredProcedure [dbo].[USP_UpdateShipment]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
---- Update
CREATE   PROC [dbo].[USP_UpdateShipment]
	@ID int,
	@ImportID int,
	@DeliverDate date,
	@ShipmentStatus nvarchar(150),
	@ShipmentId nvarchar(150),
	@ShipmentCompany nvarchar(150)
	
AS
BEGIN
	UPDATE [dbo].[ImportShipment] SET DeliverDate=@DeliverDate, ImportID=@ImportID, ShipmentStatus=@ShipmentStatus, 
	ShipmentCompany=@ShipmentCompany,  ShipmentId=@ShipmentId
	WHERE ID = @ID
END

GO
/****** Object:  StoredProcedure [dbo].[USP_UpdateUser]    Script Date: 03-Jul-20 10:45:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[USP_UpdateUser] @userName nvarchar(max), @email nvarchar(max), @password nvarchar(max)
as
begin
if(@password!='')
update Users set UserName=@userName, Password=@password where Email=@email
else
update Users set UserName=@userName where Email=@email
end
GO
USE [master]
GO
ALTER DATABASE [SportsStoreJEE] SET  READ_WRITE 
GO
