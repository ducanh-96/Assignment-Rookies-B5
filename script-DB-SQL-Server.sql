USE [master]
GO
/****** Object:  Database [dbRookiesB5]    Script Date: 6/18/2022 10:23:35 PM ******/
CREATE DATABASE [dbRookiesB5]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'dbRookiesB5', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\dbRookiesB5.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'dbRookiesB5_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\dbRookiesB5_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [dbRookiesB5] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [dbRookiesB5].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [dbRookiesB5] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [dbRookiesB5] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [dbRookiesB5] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [dbRookiesB5] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [dbRookiesB5] SET ARITHABORT OFF 
GO
ALTER DATABASE [dbRookiesB5] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [dbRookiesB5] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [dbRookiesB5] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [dbRookiesB5] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [dbRookiesB5] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [dbRookiesB5] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [dbRookiesB5] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [dbRookiesB5] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [dbRookiesB5] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [dbRookiesB5] SET  DISABLE_BROKER 
GO
ALTER DATABASE [dbRookiesB5] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [dbRookiesB5] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [dbRookiesB5] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [dbRookiesB5] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [dbRookiesB5] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [dbRookiesB5] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [dbRookiesB5] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [dbRookiesB5] SET RECOVERY FULL 
GO
ALTER DATABASE [dbRookiesB5] SET  MULTI_USER 
GO
ALTER DATABASE [dbRookiesB5] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [dbRookiesB5] SET DB_CHAINING OFF 
GO
ALTER DATABASE [dbRookiesB5] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [dbRookiesB5] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [dbRookiesB5] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [dbRookiesB5] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'dbRookiesB5', N'ON'
GO
ALTER DATABASE [dbRookiesB5] SET QUERY_STORE = OFF
GO
USE [dbRookiesB5]
GO
/****** Object:  User [adn]    Script Date: 6/18/2022 10:23:35 PM ******/
CREATE USER [adn] FOR LOGIN [adn] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[AccInfo]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccInfo](
	[info_id] [int] NOT NULL,
	[acc_id] [int] NOT NULL,
	[acc_name] [nvarchar](50) NOT NULL,
	[acc_phone] [varchar](12) NOT NULL,
	[acc_address] [nvarchar](max) NULL,
 CONSTRAINT [PK_AccInfo] PRIMARY KEY CLUSTERED 
(
	[info_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[acc_id] [int] IDENTITY(1,1) NOT NULL,
	[acc_email] [varchar](50) NOT NULL,
	[acc_password] [varchar](64) NOT NULL,
	[acc_isActived] [bit] NOT NULL,
	[role_id] [int] NOT NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[acc_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[cart_id] [int] IDENTITY(1,1) NOT NULL,
	[cart_status] [bit] NOT NULL,
	[acc_id] [int] NOT NULL,
 CONSTRAINT [PK_Cart] PRIMARY KEY CLUSTERED 
(
	[cart_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CartDetails]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CartDetails](
	[cartdt_id] [int] IDENTITY(1,1) NOT NULL,
	[cart_id] [int] NOT NULL,
	[cart_name] [nvarchar](50) NOT NULL,
	[cart_quantity] [int] NOT NULL,
	[cart_price] [decimal](15, 3) NOT NULL,
	[prod_id] [int] NOT NULL,
 CONSTRAINT [PK_CartDetails] PRIMARY KEY CLUSTERED 
(
	[cartdt_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[cat_id] [int] IDENTITY(1,1) NOT NULL,
	[cat_name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[cat_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[orderdt_id] [int] IDENTITY(1,1) NOT NULL,
	[orderdt_name] [nvarchar](50) NOT NULL,
	[orderdt_quantity] [int] NOT NULL,
	[orderdt_price] [decimal](15, 3) NOT NULL,
	[order_id] [int] NOT NULL,
	[prod_id] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[orderdt_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[order_id] [int] IDENTITY(1,1) NOT NULL,
	[order_date] [datetime] NOT NULL,
	[order_total] [decimal](15, 3) NOT NULL,
	[acc_id] [int] NOT NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductImages]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductImages](
	[img_id] [int] IDENTITY(1,1) NOT NULL,
	[img_createDate] [datetime] NULL,
	[prod_id] [int] NOT NULL,
 CONSTRAINT [PK_ProductImages] PRIMARY KEY CLUSTERED 
(
	[img_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductRatings]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductRatings](
	[rate_id] [int] IDENTITY(1,1) NOT NULL,
	[rate_name] [nvarchar](50) NOT NULL,
	[rate_star] [tinyint] NOT NULL,
	[rate_comment] [nvarchar](max) NULL,
	[rate_createDate] [datetime] NULL,
	[acc_id] [int] NOT NULL,
	[prod_id] [int] NOT NULL,
 CONSTRAINT [PK_ProductRatings] PRIMARY KEY CLUSTERED 
(
	[rate_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[prod_id] [int] IDENTITY(1,1) NOT NULL,
	[cat_id] [int] NOT NULL,
	[prod_name] [nvarchar](50) NOT NULL,
	[prod_price] [decimal](15, 3) NOT NULL,
	[prod_description] [nvarchar](max) NULL,
	[prod_createDate] [datetime] NULL,
	[prod_updateDate] [datetime] NULL,
	[prod_isOutOfStock] [bit] NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[prod_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 6/18/2022 10:23:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[role_id] [int] NOT NULL,
	[role_name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Roles] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[AccInfo]  WITH CHECK ADD  CONSTRAINT [FK_AccInfo_Accounts] FOREIGN KEY([acc_id])
REFERENCES [dbo].[Accounts] ([acc_id])
GO
ALTER TABLE [dbo].[AccInfo] CHECK CONSTRAINT [FK_AccInfo_Accounts]
GO
ALTER TABLE [dbo].[Accounts]  WITH CHECK ADD  CONSTRAINT [FK_Accounts_Roles] FOREIGN KEY([role_id])
REFERENCES [dbo].[Roles] ([role_id])
GO
ALTER TABLE [dbo].[Accounts] CHECK CONSTRAINT [FK_Accounts_Roles]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Accounts] FOREIGN KEY([acc_id])
REFERENCES [dbo].[Accounts] ([acc_id])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Accounts]
GO
ALTER TABLE [dbo].[CartDetails]  WITH CHECK ADD  CONSTRAINT [FK_CartDetails_Cart] FOREIGN KEY([cart_id])
REFERENCES [dbo].[Cart] ([cart_id])
GO
ALTER TABLE [dbo].[CartDetails] CHECK CONSTRAINT [FK_CartDetails_Cart]
GO
ALTER TABLE [dbo].[CartDetails]  WITH CHECK ADD  CONSTRAINT [FK_CartDetails_Products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[Products] ([prod_id])
GO
ALTER TABLE [dbo].[CartDetails] CHECK CONSTRAINT [FK_CartDetails_Products]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders] FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([order_id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Orders]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[Products] ([prod_id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Products]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Accounts] FOREIGN KEY([acc_id])
REFERENCES [dbo].[Accounts] ([acc_id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Accounts]
GO
ALTER TABLE [dbo].[ProductImages]  WITH CHECK ADD  CONSTRAINT [FK_ProductImages_Products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[Products] ([prod_id])
GO
ALTER TABLE [dbo].[ProductImages] CHECK CONSTRAINT [FK_ProductImages_Products]
GO
ALTER TABLE [dbo].[ProductRatings]  WITH CHECK ADD  CONSTRAINT [FK_ProductRatings_Accounts] FOREIGN KEY([acc_id])
REFERENCES [dbo].[Accounts] ([acc_id])
GO
ALTER TABLE [dbo].[ProductRatings] CHECK CONSTRAINT [FK_ProductRatings_Accounts]
GO
ALTER TABLE [dbo].[ProductRatings]  WITH CHECK ADD  CONSTRAINT [FK_ProductRatings_Products] FOREIGN KEY([prod_id])
REFERENCES [dbo].[Products] ([prod_id])
GO
ALTER TABLE [dbo].[ProductRatings] CHECK CONSTRAINT [FK_ProductRatings_Products]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories] FOREIGN KEY([cat_id])
REFERENCES [dbo].[Categories] ([cat_id])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories]
GO
USE [master]
GO
ALTER DATABASE [dbRookiesB5] SET  READ_WRITE 
GO
