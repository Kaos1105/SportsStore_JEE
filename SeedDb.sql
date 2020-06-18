USE [master]
GO
/****** Object:  Database [SportsStoreJEE]    Script Date: 18-Jun-20 5:31:27 PM ******/
CREATE DATABASE [SportsStoreJEE]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SportsStoreJEE', FILENAME = N'G:\Program File\SQL Sever\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\SportsStoreJEE.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SportsStoreJEE_log', FILENAME = N'G:\Program File\SQL Sever\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\SportsStoreJEE_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
ALTER DATABASE [SportsStoreJEE] SET QUERY_STORE = OFF
GO
USE [SportsStoreJEE]
GO
/****** Object:  Table [dbo].[Photo]    Script Date: 18-Jun-20 5:31:28 PM ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 18-Jun-20 5:31:28 PM ******/
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
INSERT [dbo].[Photo] ([Id], [Url], [IsMain], [ProductId]) VALUES (N'kixjupgkaax6qdc3eyaw', N'http://res.cloudinary.com/dufu5w88w/image/upload/v1592472946/kixjupgkaax6qdc3eyaw.jpg', 0, 1)
INSERT [dbo].[Photo] ([Id], [Url], [IsMain], [ProductId]) VALUES (N'wbocnguj4fonrpec6haf', N'http://res.cloudinary.com/dufu5w88w/image/upload/v1592474948/wbocnguj4fonrpec6haf.jpg', 1, 1)
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (1, N'Test api', N'Test api', N'Test  rest', CAST(100.00 AS Decimal(18, 2)), 150, 200, CAST(N'2020-05-05' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (2, N'Tạ', N'Tạ tay', N'Bro', CAST(50.00 AS Decimal(18, 2)), 40, 200, CAST(N'2020-05-01' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (3, N'Tạ tay', N'Bro', N'Tạ', CAST(100.00 AS Decimal(18, 2)), 50, 100, CAST(N'2020-04-01' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (4, N'Tạ đòn', N'Bros', N'Tạ', CAST(150.00 AS Decimal(18, 2)), 80, 8, CAST(N'2020-05-01' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (5, N'Ghế ngang', N'Steel', N'Ghế', CAST(200.00 AS Decimal(18, 2)), 150, 15, CAST(N'2020-04-01' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (6, N'Ghế đứng', N'Steels', N'Ghế', CAST(250.00 AS Decimal(18, 2)), 200, 200, CAST(N'2020-03-01' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (7, N'Máy đi bộ', N'Jog', N'Máy cardio', CAST(300.00 AS Decimal(18, 2)), 200, 50, CAST(N'2020-04-02' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (8, N'Máy chạy bộ edit', N'Jogss', N'Máy cardio', CAST(350.00 AS Decimal(18, 2)), 250, 50, CAST(N'2020-02-05' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (9, N'Xà đơn', N'Adidas', N'Xà', CAST(200.00 AS Decimal(18, 2)), 150, 145, CAST(N'2020-02-01' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (10, N'Test editing', N'API', N'Delete', CAST(200.00 AS Decimal(18, 2)), 50, 100, CAST(N'2020-01-01' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (11, N'Test input', N'API', N'Test', CAST(70.00 AS Decimal(18, 2)), 50, 10, CAST(N'2020-06-18' AS Date))
INSERT [dbo].[Product] ([ID], [Name], [Brand], [Category], [Price], [ImportPrice], [Stock], [DateAdded]) VALUES (14, N'Test Api edit', N'Test', N'Test', CAST(150.00 AS Decimal(18, 2)), 100, 15, CAST(N'2020-06-15' AS Date))
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0.0)) FOR [ImportPrice]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0)) FOR [Stock]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ('0001-01-01') FOR [DateAdded]
GO
ALTER TABLE [dbo].[Photo]  WITH CHECK ADD  CONSTRAINT [FK_Photo_Product_ProductId] FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Photo] CHECK CONSTRAINT [FK_Photo_Product_ProductId]
GO
/****** Object:  StoredProcedure [dbo].[USP_FilterProduct]    Script Date: 18-Jun-20 5:31:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[USP_InsertPhoto]    Script Date: 18-Jun-20 5:31:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[USP_InsertProduct]    Script Date: 18-Jun-20 5:31:28 PM ******/
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
/****** Object:  StoredProcedure [dbo].[USP_UpdateProduct]    Script Date: 18-Jun-20 5:31:28 PM ******/
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
USE [master]
GO
ALTER DATABASE [SportsStoreJEE] SET  READ_WRITE 
GO
