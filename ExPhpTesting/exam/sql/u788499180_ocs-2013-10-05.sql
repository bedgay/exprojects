
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 05, 2013 at 07:44 AM
-- Server version: 5.1.66
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u788499180_ocs`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblanswers`
--

CREATE TABLE IF NOT EXISTS `tblanswers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Content` varchar(1024) NOT NULL DEFAULT 'No answer',
  `Order` varchar(1) NOT NULL DEFAULT 'A',
  `IsTrue` tinyint(1) NOT NULL DEFAULT '0',
  `QuestionID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_question_answer` (`QuestionID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=432 ;

--
-- Dumping data for table `tblanswers`
--

INSERT INTO `tblanswers` (`ID`, `Content`, `Order`, `IsTrue`, `QuestionID`) VALUES
(25, 'A Java class can define multiple methods.', 'a', 1, 4),
(26, 'A Java class can define multiple variables.', 'b', 1, 4),
(27, 'A Java class can be defined in multiple packages.', 'c', 0, 4),
(28, 'A Java class can import multiple packages.', 'd', 1, 4),
(29, 'A Java class can’t define more than 108 constructors.', 'e', 0, 4),
(30, 'End-of-line comments can’t follow importor packagestatements.', 'f', 0, 4),
(31, 'Multiline comments can only be defined within a method definition. ', 'g', 0, 4),
(32, 'Jumpable var1 = new Jumpable();', 'a', 0, 3),
(33, 'Animal var2 = new Animal();', 'b', 1, 3),
(34, 'Lion var3 = new Animal();', 'c', 0, 3),
(35, 'Jumpable var4 = new Animal();', 'd', 0, 3),
(36, 'Jumpable var5 = new Lion();', 'e', 1, 3),
(43, 'packagestatement', 'a', 1, 5),
(44, 'importstatements', 'b', 1, 5),
(45, 'methods', 'c', 1, 5),
(46, 'variables', 'd', 1, 5),
(47, 'Java compiler ', 'e', 0, 5),
(48, 'Java Runtime Environment', 'f', 0, 5),
(49, '1, 3, 2, 5, 6, 4', 'a', 1, 6),
(50, '3, 1, 2, 5, 4, 6', 'b', 1, 6),
(51, '3, 2, 1, 4, 5, 6', 'c', 0, 6),
(52, '3, 2, 1, 5, 6, 4', 'd', 1, 6),
(53, '#connect java compiler; #connect java virtual machine; class EJavaGuru {}', 'a', 0, 7),
(54, 'package java compiler; import java virtual machine; class EJavaGuru {}', 'b', 0, 7),
(55, 'import javavirtualmachine.*; package javacompiler; class EJavaGuru { void method1() {} int count; }', 'c', 0, 7),
(56, 'package javacompiler; import javavirtualmachine.*; class EJavaGuru { void method1() {} int count; }', 'd', 1, 7),
(57, '#package javacompiler; $import javavirtualmachine; class EJavaGuru { void method1() {} int count; }', 'e', 0, 7),
(58, 'package javacompiler; import javavirtualmachine; Class EJavaGuru { void method1() {} int count; }', 'f', 0, 7),
(59, 'The imported class, java.util.Date, can be accessed only in the class Student.', 'a', 0, 8),
(60, 'The  imported  class,  java.util.Date,  can  be  accessed  by  both  the Student and Courseclasses.', 'b', 1, 8),
(61, 'Both of the classes Studentand Courseare defined in the package com.ejavaguru.', 'c', 1, 8),
(62, 'Only  the  class Student is  defined  in  the  package com.ejavaguru.  The  class Courseis defined in the default Java package.', 'd', 0, 8),
(63, 'one:two:three', 'a', 0, 9),
(64, 'EJavaGuru:one:two', 'b', 0, 9),
(65, 'java:EJavaGuru:one', 'c', 0, 9),
(66, 'two:three:four', 'd', 1, 9),
(67, 'public void main (String[] args)', 'a', 0, 10),
(68, 'public void main(String args[])', 'b', 0, 10),
(69, 'static public void main (String[] array)', 'c', 1, 10),
(70, 'public static void main (String args)', 'd', 0, 10),
(71, 'static public main (String args[])', 'e', 0, 10),
(72, 'You can start the execution of a Java application through the mainmethod.', 'a', 1, 11),
(73, 'The Java compiler calls and executes the mainmethod.', 'b', 0, 11),
(74, 'The Java Virtual Machine calls and executes the mainmethod.', 'c', 1, 11),
(75, 'A class calls and executes the mainmethod.', 'd', 0, 11),
(76, 'import mycode.com.ejavaguru.Course;', 'a', 0, 12),
(77, 'import com.ejavaguru.Course', 'b', 1, 12),
(78, 'import mycode.com.ejavaguru;', 'c', 0, 12),
(79, 'import com.ejavaguru;', 'd', 0, 12),
(80, 'import mycode.com.ejavaguru*;', 'e', 0, 12),
(81, 'import com.ejavaguru*', 'f', 0, 12),
(82, 'class EJavaGuruwill print Java.', 'a', 0, 13),
(83, 'class EJavaGuruwill print null', 'b', 0, 13),
(84, 'class EJavaGuruwon’t compile.', 'c', 1, 13),
(85, 'class EJavaGuruwill throw an exception at runtime.', 'd', 0, 13),
(86, 'The class EJavaGuruwill print Java.', 'a', 0, 14),
(87, 'The class EJavaGuruwill print null.', 'b', 0, 14),
(88, 'The class EJavaGuruwill not compile.', 'c', 1, 14),
(89, 'The class EJavaGuruwill throw an exception at runtime.', 'd', 0, 14),
(90, 'A programmer can’t define a new primitive data type', 'a', 0, 15),
(91, 'A programmer can define a new primitive data type.', 'b', 1, 15),
(92, 'Once assigned, the value of a primitive can’t be modified.', 'c', 1, 15),
(93, 'A value can’t be assigned to a primitive variable.', 'd', 1, 15),
(96, '611', 'a', 1, 16),
(97, '641', 'b', 0, 16),
(98, '930', 'c', 0, 16),
(99, '960', 'd', 0, 16),
(100, 'long, boolean, double', 'a', 1, 18),
(101, 'long, int, float', 'b', 0, 18),
(102, 'char, int, double', 'c', 0, 18),
(103, 'long, boolean, float', 'd', 1, 18),
(104, 'vgcgh', 'a', 1, 19),
(105, 'fhhbv', 'b', 0, 19),
(106, 'ggfgg', 'c', 1, 19),
(107, 'htc', 'd', 1, 19),
(108, 'bit a = 0;', 'a', 0, 20),
(109, 'integer a2 = 7;', 'b', 0, 20),
(110, 'long a3 = 0x10C', 'c', 1, 20),
(111, 'short a4 = 0512;', 'd', 1, 20),
(112, 'double a5 = 10;', 'e', 0, 20),
(113, 'byte a7 = -0;', 'f', 1, 20),
(114, 'long a8 = 123456789;', 'g', 1, 20),
(115, 'ctr += 1;', 'a', 1, 21),
(116, 'ctr =+ 1;', 'b', 0, 21),
(117, '++ctr;', 'c', 1, 21),
(118, 'ctr = 1;', 'd', 0, 21),
(119, '218', 'a', 0, 22),
(120, '232', 'b', 0, 22),
(121, '246', 'c', 0, 22),
(122, 'Compilation error', 'd', 1, 22),
(123, 'Code prints true', 'a', 0, 23),
(124, 'Code prints false', 'b', 0, 23),
(125, 'Code prints 90 >= false', 'c', 0, 23),
(126, 'Compilation error', 'd', 1, 23),
(127, 'Code prints false', 'a', 0, 24),
(128, 'Code prints true', 'b', 1, 24),
(129, 'Code will print trueif code on line 6 is modified to the following: boolean returnVal = (num1 >= 12 && num2 < 4.567) || eJavaResult ==  true;', 'c', 1, 24),
(130, 'Code will print trueif code on line 6 is modified to the following: boolean returnVal = num1 >= 12 && (num2 < 4.567 || eJavaResult ==  false);', 'd', 0, 24),
(131, 'true true false', 'a', 0, 25),
(132, '10.0 false false', 'b', 1, 25),
(133, 'false false false', 'c', 0, 25),
(134, 'Compilation error', 'd', 0, 25),
(135, 'Define instance variables as private members', 'a', 1, 26),
(136, 'Define public methods to access and modify the instance variables.', 'b', 1, 26),
(137, ' Define some of the instance variables as public members.', 'c', 0, 26),
(138, ' All of the previous.', 'd', 0, 26),
(139, 'The heightof a Personcan never be set to more than 300.', 'a', 0, 27),
(140, 'The previous code is an example of a well-encapsulated class.', 'b', 0, 27),
(141, 'The class would be better encapsulated if the height validation weren’t set to300', 'c', 0, 27),
(142, 'Even though the class isn’t well encapsulated, it can be inherited by other classes.', 'd', 1, 27),
(143, 'public void addNumbers(byte arg1, int arg2, int arg3) { double sum = arg1 + arg2 + arg3; }', 'a', 0, 28),
(144, 'public double subtractNumbers(byte arg1, int arg2, int arg3) { double sum = arg1 + arg2 + arg3; return sum; }', 'b', 1, 28),
(145, 'public double numbers(long arg1, byte arg2, double arg3) { return arg1 + arg2 + arg3; }', 'c', 0, 28),
(146, 'public float wakaWakaAfrica(long a1, long a2, short a977) { double sum = a1 + a2 + a977; return (float)sum; }', 'd', 1, 28),
(147, 'If the return type of a method is int, the method can return a value of type byte', 'a', 1, 29),
(148, 'A method may or may not return a value.', 'b', 1, 29),
(149, 'If the return type of a method is void, it can define a return statement without a value, as follows: return;', 'c', 1, 29),
(150, 'A method may or may not accept any method arguments.', 'd', 1, 29),
(151, 'A method should accept at least one method argument or define its return type.', 'e', 0, 29),
(152, 'A method whose return type is Stringcan’t return null.', 'f', 0, 29),
(153, 'anotherMethod anotherMethod someMethod someMethod', 'a', 0, 30),
(154, 'anotherMethod EJava someMethod someMethod', 'b', 1, 30),
(155, 'anotherMethod EJava someMethod EJava', 'c', 0, 30),
(156, 'Compilation error.', 'd', 0, 30),
(157, '20 10 11 11', 'a', 0, 31),
(158, '20 20 11 10', 'b', 0, 31),
(159, '20 10 11 10', 'c', 1, 31),
(160, 'Compilation error', 'd', 0, 31),
(161, 'private String eJava(int val, String firstName, double dur)', 'a', 0, 32),
(162, 'public void eJava(int val1, String val2, double val3)', 'b', 0, 32),
(163, 'String eJava(String name, int age, double duration)', 'c', 1, 32),
(164, 'float eJava(double name, String age, byte duration)', 'd', 1, 32),
(165, 'ArrayList<String> eJava()', 'e', 1, 32),
(166, 'char[] eJava(double numbers)', 'f', 1, 32),
(167, 'String eJava()', 'g', 1, 32),
(168, 'Compilation error', 'a', 0, 33),
(169, 'Runtime exception', 'b', 0, 33),
(170, 'int String', 'c', 1, 33),
(171, 'long Object', 'd', 0, 33),
(172, 'Compilation error', 'a', 0, 34),
(173, 'Runtime exception', 'b', 0, 34),
(174, 'int String', 'c', 1, 34),
(175, 'long Object', 'd', 0, 34),
(176, 'The class EJava defines three overloaded constructors.', 'a', 1, 35),
(177, 'The class EJavadefines two overloaded constructors. The private constructor isn’t counted as an overloaded constructor.', 'b', 0, 35),
(178, 'Constructors with different access modifiers can’t call each other.', 'c', 0, 35),
(179, 'The code prints the following: protected private public', 'd', 1, 35),
(180, 'The code prints the following: public private protected', 'e', 0, 35),
(181, 'If a user defines a privateconstructor for a publicclass, Java creates a public default constructor for the class.', 'a', 1, 36),
(182, 'A class that gets a default constructor doesn’t have overloaded constructors', 'b', 0, 36),
(183, 'A user can overload the default constructor of a class.', 'c', 1, 36),
(184, 'The following class is eligible for default constructor: class EJava {}', 'd', 0, 36),
(185, 'The following class is also eligible for a default constructor: class EJava { void EJava() {} }', 'e', 0, 36),
(186, 'The code will print Java.', 'a', 0, 37),
(187, 'The code will print Oracle.', 'b', 0, 37),
(188, 'The code will not compile', 'c', 0, 37),
(189, 'The code will throw an exception or an error at runtime.', 'd', 1, 37),
(190, 'You cannot handle runtime exceptions.', 'a', 0, 38),
(191, 'You should not handle errors', 'b', 1, 38),
(192, 'If  a  method  throws  a  checked  exception,  it  must  be  either  handled  by  the method or specified in its throwsclause. ', 'c', 1, 38),
(193, 'If  a  method  throws  a  runtime  exception,  it  may  include  the  exception  in  its throws clause.', 'd', 1, 38),
(194, 'Runtime exceptions are checked exceptions.', 'e', 0, 38),
(195, 'If  the  class EJavaGuruExcep2 is  executed  using  the  following  command,  it prints NullPointerException: javaEJavaGuruExcep2', 'a', 0, 39),
(196, 'If  the  class EJavaGuruExcep2 is  executed  using  the  following  command,  it prints IndexOutOfBoundsException: javaEJavaGuruExcep2', 'b', 0, 39),
(197, 'If  the  class EJavaGuruExcep2 is  executed  using  the  following  command,  it prints ArrayIndexOutOfBoundsException: javaEJavaGuruExcep2one', 'c', 0, 39),
(198, 'The code will fail to compile.', 'd', 0, 39),
(199, 'guru finally 1', 'a', 0, 40),
(200, 'guru finally 1 Exception in thread "main" java.lang.StackOverflowError', 'b', 1, 40),
(201, 'guru Exception in thread "main" java.lang.StackOverflowError', 'c', 0, 40),
(202, 'guru', 'd', 0, 40),
(203, 'The code fails to compile.', 'g', 0, 40),
(204, 'Exceptions enable a developer to define the programming logic separate from the exception-handling code.', 'a', 0, 41),
(205, 'Exception handling speeds up execution of the code.', 'b', 1, 41),
(206, 'Exception handing is used to define code that should execute when a piece of code throws an exception.', 'c', 0, 41),
(207, 'Code  that  handles  all  the  checked  exceptions  can  still  throw  unchecked exceptions.', 'd', 0, 41),
(208, 'java.lang.Throwableis the base class of all type of exceptions.', 'a', 0, 42),
(209, 'If a class is a subclass of java.lang.Exception, it may or may not be a checked exception.', 'b', 0, 42),
(210, 'Erroris an unchecked exception.', 'c', 1, 42),
(211, 'Errorand checked exceptions need not be part of a method signature.', 'd', 1, 42),
(212, '10', 'a', 0, 43),
(213, '11', 'b', 0, 43),
(214, '12', 'c', 1, 43),
(215, 'Compilation error', 'd', 0, 43),
(216, 'Runtime exception ', 'e', 0, 43),
(217, 'Base', 'a', 0, 44),
(218, 'Derived', 'b', 1, 44),
(219, 'Derived Base', 'c', 0, 44),
(220, 'Base Derived', 'd', 0, 44),
(221, 'Compilation error', 'e', 0, 44),
(222, 'A  user-defined  class  may  not  throw  an IllegalStateException.  It  must  be thrown only by Java APIclasses.', 'a', 0, 45),
(223, 'System.out.println will  throw  NullPointerException if  an  uninitialized instance variable of type Stringis passed to it to print its value.', 'b', 0, 45),
(224, 'NumberFormatExceptionis thrown by multiple methods from the Java APIwhen invalid numbers are passed on as Strings to be converted to the specified number format.', 'c', 1, 45),
(225, 'ExceptionInInitializerErrormay be thrown by the JVMwhen a staticinitializer in your code throws a NullPointerException.', 'd', 1, 45),
(226, '1 inner 2 outer', 'a', 0, 46),
(227, '1 outer', 'b', 0, 46),
(228, '1 inner', 'c', 0, 46),
(229, '1 inner 2', 'd', 1, 46),
(230, 'Animal Rabbit', 'a', 1, 47),
(231, 'Cat Rabbit', 'b', 0, 47),
(232, 'Animal Animal', 'c', 0, 47),
(233, 'None of the above', 'd', 0, 47),
(234, 'The output of the code is: Flower', 'a', 0, 48),
(235, 'The output of the code is: Rose', 'b', 0, 48),
(236, 'The output of the code is: Lily', 'c', 0, 48),
(237, 'The code fails to compile.', 'd', 1, 48),
(238, 'void walk(Movable movable) {', 'a', 1, 49),
(239, 'void walk(Person movable) {', 'b', 1, 49),
(240, 'void walk(Vehicle movable) {', 'c', 1, 49),
(241, 'void walk() {', 'd', 0, 49),
(242, 'Only an abstractclass can be used as a base class to implement polymorphism with classes.', 'a', 0, 50),
(243, 'Polymorphic methods are also called overridden methods', 'b', 1, 50),
(244, 'In polymorphism, depending on the exact type of object, the JVMexecutes the appropriate method at compile time.', 'c', 0, 50),
(245, 'None of the above.', 'd', 0, 50),
(246, 'The code exhibits polymorphism with classes.', 'a', 0, 51),
(247, 'The code exhibits polymorphism with interfaces', 'b', 0, 51),
(248, 'The code exhibits polymorphism with classes and interfaces.', 'c', 0, 51),
(249, 'None of the above', 'd', 1, 51),
(250, 'Inheritance enables you to reuse existing code.', 'a', 1, 52),
(251, 'Inheritance saves you from having to modify common code in multiple classes.', 'b', 1, 52),
(252, 'Polymorphism passes special instructions to the compiler so that the code can run on multiple platforms.', 'c', 0, 52),
(253, 'Polymorphic methods cannot throw exceptions.', 'd', 0, 52),
(254, 'Given the following code, which of the options are true? class Satellite { void orbit() {} } class Moon extends Satellite { void orbit() {} } class ArtificialSatellite extends Satellite { void orbit() {} }', 'a', 1, 53),
(255, 'Only  the  method orbit defined  in  the  classes Satellite and  ArtificialSatelliteis polymorphic.', 'b', 0, 53),
(256, 'Only the method orbitdefined in the class ArtificialSatelliteis polymorphic.', 'c', 0, 53),
(257, 'None of the above.', 'd', 0, 53),
(258, 'Programmer b = new Programmer();', 'a', 0, 54),
(259, 'Programmer b = new Author();', 'b', 1, 54),
(260, 'Author b = new Author(); ', 'c', 1, 54),
(261, 'Author b = new Programmer();', 'd', 0, 54),
(262, 'Programmer b = ((Author)new Programmer());', 'e', 0, 54),
(263, 'Author b = ((Author)new Programmer());', 'f', 0, 54),
(264, 'Modify code on line 2 to: interface Printable {', 'a', 0, 55),
(265, 'Modify code on line 3 to: public String print();', 'b', 0, 55),
(266, 'Define the accessibility of the printmethods to publicon lines 6 and 9.', 'c', 1, 55),
(267, 'Modify code on line 8 so that it implements only the interface Printable.', 'd', 0, 55),
(268, 'EJava EJava EJava Guru', 'a', 1, 56),
(269, 'EJava Guru EJava Guru', 'b', 0, 56),
(270, 'EJava EJava EJava EJava', 'c', 0, 56),
(271, 'EJava Guru Guru Guru', 'd', 0, 56),
(272, '10', 'a', 0, 57),
(273, '30', 'b', 1, 57),
(274, '31', 'c', 0, 57),
(275, '32', 'd', 0, 57),
(276, '10', 'a', 0, 58),
(277, '30', 'b', 0, 58),
(278, '31', 'c', 0, 58),
(279, '32', 'd', 1, 58),
(280, 'The enhanced forloop can’t be used within a regular forloop.', 'a', 0, 59),
(281, 'The enhanced forloop can’t be used within a whileloop.', 'b', 0, 59),
(282, 'The enhanced forloop can be used within a do-whileloop.', 'c', 1, 59),
(283, 'The enhanced forloop can’t be used within a switchconstruct.', 'd', 0, 59),
(284, 'All of the above statements are false.', 'e', 0, 59),
(285, 'true false ABC', 'a', 0, 60),
(286, 'false ABC', 'b', 1, 60),
(287, 'true ABC', 'c', 0, 60),
(288, 'Compilation error', 'd', 0, 60),
(289, 'case 10*3: System.out.println(2);', 'a', 1, 61),
(290, 'case num: System.out.println(3);', 'b', 0, 61),
(291, 'case 10/3: System.out.println(4);', 'c', 1, 61),
(292, 'case num2: System.out.println(5);', 'd', 1, 61),
(293, 'default', 'a', 0, 62),
(294, 'default 4', 'b', 0, 62),
(295, '4', 'c', 0, 62),
(296, 'Compilation error', 'd', 1, 62),
(297, 'default case1 case2', 'a', 0, 63),
(298, 'case1 case2', 'b', 0, 63),
(299, 'case2', 'c', 0, 63),
(300, 'Compilation error', 'd', 1, 63),
(301, 'Runtime exception', 'e', 0, 63),
(302, 'ejava enum guru', 'a', 1, 64),
(303, 'ejava', 'b', 0, 64),
(304, 'ejavaguru e', 'c', 0, 64),
(305, 'ejava enum guru ejavaguru', 'd', 0, 64),
(306, 'else', 'a', 0, 65),
(307, '0 1 2', 'b', 0, 65),
(308, '0 1', 'c', 1, 65),
(309, 'Compilation error', 'd', 0, 65),
(310, 'Compilation error', 'a', 0, 66),
(311, '0 5', 'b', 0, 66),
(312, '0 5 10', 'c', 0, 66),
(313, '10', 'd', 0, 66),
(314, '0 1 5', 'e', 0, 66),
(315, '5', 'f', 1, 66),
(316, 'int[][] array1 = {{1, 2, 3}, {}, {1, 2,3, 4, 5}};', 'a', 1, 67),
(317, 'int[][] array2 = new array() {{1, 2, 3}, {}, {1, 2,3, 4, 5}};', 'b', 0, 67),
(318, 'int[][] array3 = {1, 2, 3}, {0}, {1, 2,3, 4, 5};', 'c', 0, 67),
(319, 'int[][] array5 = new int[2][];', 'd', 1, 67),
(320, 'By default, an ArrayListcreates an array with an initial size of 16 to store its elements.', 'a', 0, 68),
(321, 'Because ArrayListstores only objects, you can’t pass element of an ArrayList to a switchconstruct.', 'b', 0, 68),
(322, 'Calling clear()or remove()on an ArrayList, will remove all its elements', 'c', 0, 68),
(323, 'If you frequently add elements to an ArrayList, specifying a larger capacity will improve the code efficiency.', 'd', 1, 68),
(324, 'Calling the method clone()on an ArrayListcreates its shallow copy; that is, it doesn’t clone the individual list elements.', 'e', 1, 68),
(325, 'An ArrayListoffers a resizable array, which is easily managed using the methods it provides. You can add and remove elements from an ArrayList.', 'a', 1, 69),
(326, 'Values stored by an ArrayListcan be modified.', 'b', 1, 69),
(327, 'You can iterate through elements of an ArrayListusing a forloop, Iterator, or ListIterator.', 'c', 1, 69),
(328, 'An ArrayList requires you to specify the total elements before you can store any elements in it.', 'd', 0, 69),
(329, 'An ArrayListcan store any type of object.', 'e', 1, 69),
(330, 'true printed once', 'a', 0, 70),
(331, 'false printed once', 'b', 0, 70),
(332, 'true printed in an infinite loop', 'c', 1, 70),
(333, 'false printed in an infinite loop', 'd', 0, 70),
(431, 'fghjfghjfghjfgh', 'z', 1, 71),
(430, 'hjfghjgfhj', 'y', 1, 71),
(429, 'ghjfgjhfgjhfg', 'x', 1, 71),
(427, 'fghjfgjh', 'c', 1, 72),
(426, 'jfghjgfhj', 'b', 1, 72),
(425, 'fghjgfhj', 'a', 1, 72),
(349, 'fghjfghj', 'a', 1, 73),
(350, 'fghjgfhj', 'b', 0, 73),
(351, 'fgjgfhjfg', 'c', 0, 73),
(360, '', '3', 1, 74),
(359, '', '2', 1, 74),
(358, '', '1', 1, 74),
(366, 'hgjkghjkghjkghjkghjkh', '3', 1, 75),
(365, 'hjkghjkghjk', '2', 1, 75),
(364, 'hjhgjkghjkghj', '1', 1, 75),
(375, 'ccc', 'c', 1, 76),
(374, 'bbb', 'b', 1, 76),
(373, 'aaa', 'a', 1, 76),
(428, 'ghjgjghj', 'q', 1, 71),
(389, 'ghjkghjk', '3', 1, 77),
(388, 'hgjkhj', '2', 1, 77),
(387, 'ghjk', '1', 1, 77),
(390, 'gfhjfh', 'z', 1, 78),
(391, 'jgfhjfg', 'x', 1, 78),
(392, 'hjfgh', 'c', 1, 78);

-- --------------------------------------------------------

--
-- Table structure for table `tblcategories`
--

CREATE TABLE IF NOT EXISTS `tblcategories` (
  `ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL DEFAULT 'No name',
  `Image` varchar(100) NOT NULL DEFAULT 'No image',
  `Order` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `tblcategories`
--

INSERT INTO `tblcategories` (`ID`, `Name`, `Image`, `Order`) VALUES
(1, 'OCA Java SE 7 Programmer I Certification', 'No image', 0),
(3, 'Oracle Certified Professional Java SE 7 Programmer', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblquestions`
--

CREATE TABLE IF NOT EXISTS `tblquestions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Content` text NOT NULL,
  `Order` tinyint(1) NOT NULL DEFAULT '0',
  `SectionID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_section_question` (`SectionID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=79 ;

--
-- Dumping data for table `tblquestions`
--

INSERT INTO `tblquestions` (`ID`, `Content`, `Order`, `SectionID`) VALUES
(3, '<p>Given the following definition of the classes Animal, Lion, and Jumpable,</p><p>select the correct combinations of assignments of a variable (select 2 options):</p><p>interface Jumpable {}</p><p>class Animal {}</p><p>class Lion extends Animal implements Jumpable {}</p>', 1, 4),
(4, 'Which of the following statements are true? (Select 3 options.)', 2, 4),
(5, 'What are the valid components of a Java source file (choose all that apply)', 1, 5),
(6, 'The&nbsp; following&nbsp; numbered&nbsp; list&nbsp; of&nbsp; Java&nbsp; class&nbsp; components&nbsp; is&nbsp; not&nbsp; in&nbsp; any&nbsp; particular<br>order. Select the correct order of their occurrence in a Java class (choose all that apply):<br>1 comments<br>2 import statement<br>3 package statement<br>4 methods<br>5 class declaration<br>6 variables<br>', 2, 5),
(7, 'Which of the following examples define the correct Java class structure?', 3, 5),
(8, 'Given the following contents of the Java source code file MyClass.java, select the<br>correct options:', 4, 5),
(9, '<b>Given the following definition of the class EJavaGuru,</b><br><span style="color:rgb(0,0,255);"><span style="font-size: 12px; color: rgb(0, 0, 255);"><i style="font-size: 12px; color: rgb(0, 0, 255);">class EJavaGuru {<br style="font-size: 12px; color: rgb(0, 0, 255);">public static void main(String[] args) {<br style="font-size: 12px; color: rgb(0, 0, 255);">System.out.println(args[1]+":"+ args[2]+":"+ args[3]);<br style="font-size: 12px; color: rgb(0, 0, 255);">}<br style="font-size: 12px; color: rgb(0, 0, 255);">}</i></span><br><b>what is the output of the previous class, if it is executed using the command:</b><br><i>java EJavaGuru one two three four</i>', 5, 5),
(10, '<b>Which of the following options, when inserted at //INSERT CODE HERE, will print<br>out EJavaGuru?</b><br><i><span style="font-size:12px;color: rgb(0, 0, 255);">public class EJavaGuru {<br style="color: rgb(0, 0, 255); font-size: 12px;">// INSERT CODE HERE <br style="color: rgb(0, 0, 255); font-size: 12px;">{<br style="color: rgb(0, 0, 255); font-size: 12px;">System.out.println("EJavaGuru");<br style="color: rgb(0, 0, 255); font-size: 12px;">}<br style="color: rgb(0, 0, 255); font-size: 12px;">}</span></i>', 6, 5),
(11, 'Select the correct options:', 7, 5),
(12, '<b>A class Courseis defined in a package com.ejavaguru. Given that the physical location of the corresponding class file is /mycode/com/ejavaguru/Course.class and execution takes place within the mycode directory, which of the following lines of code, when<br>inserted at // INSERT CODE HERE, will import the Courseclass into the class MyCourse?</b><br><i><span style="font-size:12px;color: rgb(0, 0, 255);"><br style="color: rgb(0, 0, 255); font-size: 12px;">// INSERT CODE HERE<br style="color: rgb(0, 0, 255); font-size: 12px;">class MyCourse {<br style="color: rgb(0, 0, 255); font-size: 12px;">Course c;<br style="color: rgb(0, 0, 255); font-size: 12px;">}</span></i>', 8, 5),
(13, '<b>Examine the following code:</b><br><i><span style="color: rgb(0, 0, 255); font-size: 14px;">class Course {<br style="color: rgb(0, 0, 255); font-size: 14px;">String courseName;<br style="color: rgb(0, 0, 255); font-size: 14px;">}<br style="color: rgb(0, 0, 255); font-size: 14px;">class EJavaGuru {<br style="color: rgb(0, 0, 255); font-size: 14px;">public static void main(String args[]) {<br style="color: rgb(0, 0, 255); font-size: 14px;">Course c = new Course();<br style="color: rgb(0, 0, 255); font-size: 14px;">c.courseName = "Java";<br style="color: rgb(0, 0, 255); font-size: 14px;">System.out.println(c.courseName);<br style="color: rgb(0, 0, 255); font-size: 14px;">}<br style="color: rgb(0, 0, 255); font-size: 14px;">}</span></i><br><b>Which of the following statements will be true if the variable courseNameis defined as<br>a privatevariable?</b>', 9, 5),
(14, '<b>Given the following definition of the class Course,</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">package com.ejavaguru.courses;<br style="color: rgb(0, 0, 255);">class Course {<br style="color: rgb(0, 0, 255);">public String courseName;<br style="color: rgb(0, 0, 255);">}</i></span><br><b>what’s the output of the following code?</b><br><i><span style="color:rgb(0,0,255);">package com.ejavaguru;<br style="color: rgb(0, 0, 255);">import com.ejavaguru.courses.Course;<br style="color: rgb(0, 0, 255);">class EJavaGuru {<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">Course c = new Course();<br style="color: rgb(0, 0, 255);">c.courseName = "Java";<br style="color: rgb(0, 0, 255);">System.out.println(c.courseName);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</span></i>', 10, 5),
(15, 'Select all incorrect statements:', 1, 6),
(16, 'What is the output of the following code?<br>public class Foo {<br>public static void main(String[] args) {<br>int a = 10;<br>long b = 20;<br>short c = 30;<br>System.out.println(++a + b++ * c);<br>}<br>}', 3, 6),
(18, 'Select the option(s) that is/are the best choice for the following:<br>___________________ should be used to store a count of cars manufactured by a car<br>manufacturing company. _________________ should be used to store whether this<br>car&nbsp; manufacturing&nbsp; company&nbsp; modifies&nbsp; the&nbsp; interiors&nbsp; on&nbsp; the&nbsp; customer’s&nbsp; request.<br>____________ should be used to store the maximum speed of a car.', 4, 6),
(71, '<p>Nh&#7919;ng l&#7899;p v?/ho&#7863;c &#273;&#7889;i t&#432;&#7907;ng trong m&#7851;u thi&#7871;t k&#7871; n?y:</p><p>- <i>Target (ChemicalCompound)</i>: &#273;&#7883;nh ngh&#297;a m&#7897;t domain-specific interface m? Client s&#7917; d&#7909;ng.</p><p>- <i>Adapter (Compound)</i>: l?m cho Adaptee interface t&#432;&#417;ng th?ch v&#7899;i Target interface.</p><p>- <i>Adaptee (ChemicalDatabank)</i>: x?c &#273;&#7883;nh m&#7897;t interface &#273;ang t&#7891;n t&#7841;i c&#7847;n l?m t&#432;&#417;ng th?ch.</p><p>- <i>Client (AdapterApp)</i>: ph&#7889;i h&#7907;p c?c &#273;&#7889;i t&#432;&#7907;ng ph? h&#7907;p cho Target interface.</p><p><b>Adapter: khi n?o d?ng v? d?ng &#7903; &#273;?u</b></p><p>C?c l&#7853;p tr?nh vi?n .NET vi&#7871;t c?c l&#7899;p &#273;&#432;a ra c?c ph&#432;&#417;ng th&#7913;c &#273;&#432;&#7907;c g&#7885;i b&#7903;i c?c client. H&#7847;u h&#7871;t th&#7901;i gian ch?ng c? th&#7875; &#273;i&#7873;u khi&#7875;n c?c interface, nh&#432;ng c? m&#7897;t s&#7889; tr&#432;&#7901;ng h&#7907;p, ch&#7859;ng h&#7841;n khi d?ng c?c th&#432; vi?n c&#7911;a h?ng th&#7913; 3, n&#417;i ch?ng kh?ng th&#7875; l?m &#273;&#432;&#7907;c &#273;i&#7873;u &#273;?. Th&#432; vi&#7879;n c&#7911;a h?ng th&#7913; 3 th&#7921;c thi c?c d&#7883;ch v&#7909; &#273;&#432;&#7907;c mong &#273;&#7907;i nh&#432;ng c?c ph&#432;&#417;ng th&#7913;c c&#7911;a interface v? c?c t?n c&#7911;a thu&#7897;c t?nh kh?c bi&#7879;t so v&#7899;i nh&#7919;ng g? client mong &#273;&#7907;i. &#272;?y l? m&#7897;t tr&#432;&#7901;ng h&#7907;p m? b&#7841;n c&#7847;n s&#7917; d&#7909;ng m&#7851;u thi&#7871;t k&#7871; Adapter. Adapter cung c&#7845;p m&#7897;t interface m? client mong &#273;&#7907;i s&#7917; d&#7909;ng nh&#7919;ng d&#7883;ch v&#7909; c&#7911;a m&#7897;t l&#7899;p v&#7899;i m&#7897;t interface kh?c. C?c adapter th&#432;&#7901;ng &#273;&#432;&#7907;c s&#7917; d&#7909;ng trong c?c m?i tr&#432;&#7901;ng l&#7853;p tr?nh n&#417;i m? c?c th?nh ph&#7847;n ho&#7863;c c?c &#7913;ng d&#7909;ng m&#7899;i c&#7847;n &#273;&#432;&#7907;c t?ch h&#7907;p v? l?m vi&#7879;c v&#7899;i nhau v&#7899;i c?c th?nh ph&#7847;n &#273;ang t&#7891;n t&#7841;i.</p><p>C?c adapter c&#361;ng h&#7919;u ?ch trong c?c tr&#432;&#7901;ng h&#7907;p refactor. Gi&#7843; s&#7917; b&#7841;n c? 2 l&#7899;p th&#7921;c thi c?c ch&#7913;c n&#259;ng kh?c nhau nh&#432;ng c? c?c interface kh?c nhau. Client s&#7917; d&#7909;ng c&#7843; 2 l&#7899;p n?y, nh&#432;ng ph&#7847;n code th? s&#7869; kh?ng th&#7853;t r? r?ng v? &#273;&#417;n gi&#7843;n h&#417;n &#273;&#7875; hi&#7875;u n&#7871;u ch?ng c?ng chia s&#7867; m&#7897;t interface. B&#7841;n kh?ng th&#7875; thay th&#7871; interface n?y, nh&#432;ng b&#7841;n c? th&#7875; ?che? nh&#7919;ng kh?c bi&#7879;t n?y b&#7857;ng c?ch s&#7917; d&#7909;ng Adapter cho ph?p client k&#7871;t n&#7889;i th?ng qua m&#7897;t interface chung. Adapter n?y x&#7917; l? vi&#7879;c ?nh x&#7841; gi&#7919;a interface &#273;&#432;&#7907;c chia s&#7867; v? c?c interface g&#7889;c.</p><p>Nh&#7919;ng l&#7899;p v?/ho&#7863;c &#273;&#7889;i t&#432;&#7907;ng trong m&#7851;u thi&#7871;t k&#7871; n?y:</p><p>-?<i>Target (ChemicalCompound)</i>: &#273;&#7883;nh ngh&#297;a m&#7897;t domain-specific interface m? Client s&#7917; d&#7909;ng.</p><p>-?<i>Adapter (Compound)</i>: l?m cho Adaptee interface t&#432;&#417;ng th?ch v&#7899;i Target interface.</p><p>-?<i>Adaptee (ChemicalDatabank)</i>: x?c &#273;&#7883;nh m&#7897;t interface &#273;ang t&#7891;n t&#7841;i c&#7847;n l?m t&#432;&#417;ng th?ch.</p><p>-?<i>Client (AdapterApp)</i>: ph&#7889;i h&#7907;p c?c &#273;&#7889;i t&#432;&#7907;ng ph? h&#7907;p cho Target interface.</p><p><b>Adapter: khi n?o d?ng v? d?ng &#7903; &#273;?u</b></p><p>C?c l&#7853;p tr?nh vi?n .NET vi&#7871;t c?c l&#7899;p &#273;&#432;a ra c?c ph&#432;&#417;ng th&#7913;c &#273;&#432;&#7907;c g&#7885;i b&#7903;i c?c client. H&#7847;u h&#7871;t th&#7901;i gian ch?ng c? th&#7875; &#273;i&#7873;u khi&#7875;n c?c interface, nh&#432;ng c? m&#7897;t s&#7889; tr&#432;&#7901;ng h&#7907;p, ch&#7859;ng h&#7841;n khi d?ng c?c th&#432; vi?n c&#7911;a h?ng th&#7913; 3, n&#417;i ch?ng kh?ng th&#7875; l?m &#273;&#432;&#7907;c &#273;i&#7873;u &#273;?. Th&#432; vi&#7879;n c&#7911;a h?ng th&#7913; 3 th&#7921;c thi c?c d&#7883;ch v&#7909; &#273;&#432;&#7907;c mong &#273;&#7907;i nh&#432;ng c?c ph&#432;&#417;ng th&#7913;c c&#7911;a interface v? c?c t?n c&#7911;a thu&#7897;c t?nh kh?c bi&#7879;t so v&#7899;i nh&#7919;ng g? client mong &#273;&#7907;i. &#272;?y l? m&#7897;t tr&#432;&#7901;ng h&#7907;p m? b&#7841;n c&#7847;n s&#7917; d&#7909;ng m&#7851;u thi&#7871;t k&#7871; Adapter. Adapter cung c&#7845;p m&#7897;t interface m? client mong &#273;&#7907;i s&#7917; d&#7909;ng nh&#7919;ng d&#7883;ch v&#7909; c&#7911;a m&#7897;t l&#7899;p v&#7899;i m&#7897;t interface kh?c. C?c adapter th&#432;&#7901;ng &#273;&#432;&#7907;c s&#7917; d&#7909;ng trong c?c m?i tr&#432;&#7901;ng l&#7853;p tr?nh n&#417;i m? c?c th?nh ph&#7847;n ho&#7863;c c?c &#7913;ng d&#7909;ng m&#7899;i c&#7847;n &#273;&#432;&#7907;c t?ch h&#7907;p v? l?m vi&#7879;c v&#7899;i nhau v&#7899;i c?c th?nh ph&#7847;n &#273;ang t&#7891;n t&#7841;i.</p><p>C?c adapter c&#361;ng h&#7919;u ?ch trong c?c tr&#432;&#7901;ng h&#7907;p refactor. Gi&#7843; s&#7917; b&#7841;n c? 2 l&#7899;p th&#7921;c thi c?c ch&#7913;c n&#259;ng kh?c nhau nh&#432;ng c? c?c interface kh?c nhau. Client s&#7917; d&#7909;ng c&#7843; 2 l&#7899;p n?y, nh&#432;ng ph&#7847;n code th? s&#7869; kh?ng th&#7853;t r? r?ng v? &#273;&#417;n gi&#7843;n h&#417;n &#273;&#7875; hi&#7875;u n&#7871;u ch?ng c?ng chia s&#7867; m&#7897;t interface. B&#7841;n kh?ng th&#7875; thay th&#7871; interface n?y, nh&#432;ng b&#7841;n c? th&#7875; ?che? nh&#7919;ng kh?c bi&#7879;t n?y b&#7857;ng c?ch s&#7917; d&#7909;ng Adapter cho ph?p client k&#7871;t n&#7889;i th?ng qua m&#7897;t interface chung. Adapter n?y x&#7917; l? vi&#7879;c ?nh x&#7841; gi&#7919;a interface &#273;&#432;&#7907;c chia s&#7867; v? c?c interface g&#7889;c.</p><p>Nh&#7919;ng l&#7899;p v?/ho&#7863;c &#273;&#7889;i t&#432;&#7907;ng trong m&#7851;u thi&#7871;t k&#7871; n?y:</p><p>-?<i>Target (ChemicalCompound)</i>: &#273;&#7883;nh ngh&#297;a m&#7897;t domain-specific interface m? Client s&#7917; d&#7909;ng.</p><p>-?<i>Adapter (Compound)</i>: l?m cho Adaptee interface t&#432;&#417;ng th?ch v&#7899;i Target interface.</p><p>-?<i>Adaptee (ChemicalDatabank)</i>: x?c &#273;&#7883;nh m&#7897;t interface &#273;ang t&#7891;n t&#7841;i c&#7847;n l?m t&#432;&#417;ng th?ch.</p><p>-?<i>Client (AdapterApp)</i>: ph&#7889;i h&#7907;p c?c &#273;&#7889;i t&#432;&#7907;ng ph? h&#7907;p cho Target interface.</p><p><b>Adapter: khi n?o d?ng v? d?ng &#7903; &#273;?u</b></p><p>C?c l&#7853;p tr?nh vi?n .NET vi&#7871;t c?c l&#7899;p &#273;&#432;a ra c?c ph&#432;&#417;ng th&#7913;c &#273;&#432;&#7907;c g&#7885;i b&#7903;i c?c client. H&#7847;u h&#7871;t th&#7901;i gian ch?ng c? th&#7875; &#273;i&#7873;u khi&#7875;n c?c interface, nh&#432;ng c? m&#7897;t s&#7889; tr&#432;&#7901;ng h&#7907;p, ch&#7859;ng h&#7841;n khi d?ng c?c th&#432; vi?n c&#7911;a h?ng th&#7913; 3, n&#417;i ch?ng kh?ng th&#7875; l?m &#273;&#432;&#7907;c &#273;i&#7873;u &#273;?. Th&#432; vi&#7879;n c&#7911;a h?ng th&#7913; 3 th&#7921;c thi c?c d&#7883;ch v&#7909; &#273;&#432;&#7907;c mong &#273;&#7907;i nh&#432;ng c?c ph&#432;&#417;ng th&#7913;c c&#7911;a interface v? c?c t?n c&#7911;a thu&#7897;c t?nh kh?c bi&#7879;t so v&#7899;i nh&#7919;ng g? client mong &#273;&#7907;i. &#272;?y l? m&#7897;t tr&#432;&#7901;ng h&#7907;p m? b&#7841;n c&#7847;n s&#7917; d&#7909;ng m&#7851;u thi&#7871;t k&#7871; Adapter. Adapter cung c&#7845;p m&#7897;t interface m? client mong &#273;&#7907;i s&#7917; d&#7909;ng nh&#7919;ng d&#7883;ch v&#7909; c&#7911;a m&#7897;t l&#7899;p v&#7899;i m&#7897;t interface kh?c. C?c adapter th&#432;&#7901;ng &#273;&#432;&#7907;c s&#7917; d&#7909;ng trong c?c m?i tr&#432;&#7901;ng l&#7853;p tr?nh n&#417;i m? c?c th?nh ph&#7847;n ho&#7863;c c?c &#7913;ng d&#7909;ng m&#7899;i c&#7847;n &#273;&#432;&#7907;c t?ch h&#7907;p v? l?m vi&#7879;c v&#7899;i nhau v&#7899;i c?c th?nh ph&#7847;n &#273;ang t&#7891;n t&#7841;i.</p><p>C?c adapter c&#361;ng h&#7919;u ?ch trong c?c tr&#432;&#7901;ng h&#7907;p refactor. Gi&#7843; s&#7917; b&#7841;n c? 2 l&#7899;p th&#7921;c thi c?c ch&#7913;c n&#259;ng kh?c nhau nh&#432;ng c? c?c interface kh?c nhau. Client s&#7917; d&#7909;ng c&#7843; 2 l&#7899;p n?y, nh&#432;ng ph&#7847;n code th? s&#7869; kh?ng th&#7853;t r? r?ng v? &#273;&#417;n gi&#7843;n h&#417;n &#273;&#7875; hi&#7875;u n&#7871;u ch?ng c?ng chia s&#7867; m&#7897;t interface. B&#7841;n kh?ng th&#7875; thay th&#7871; interface n?y, nh&#432;ng b&#7841;n c? th&#7875; ?che? nh&#7919;ng kh?c bi&#7879;t n?y b&#7857;ng c?ch s&#7917; d&#7909;ng Adapter cho ph?p client k&#7871;t n&#7889;i th?ng qua m&#7897;t interface chung. Adapter n?y x&#7917; l? vi&#7879;c ?nh x&#7841; gi&#7919;a interface &#273;&#432;&#7907;c chia s&#7867; v? c?c interface g&#7889;c.</p><p>Nh&#7919;ng l&#7899;p v?/ho&#7863;c &#273;&#7889;i t&#432;&#7907;ng trong m&#7851;u thi&#7871;t k&#7871; n?y:</p><p>-?<i>Target (ChemicalCompound)</i>: &#273;&#7883;nh ngh&#297;a m&#7897;t domain-specific interface m? Client s&#7917; d&#7909;ng.</p><p>-?<i>Adapter (Compound)</i>: l?m cho Adaptee interface t&#432;&#417;ng th?ch v&#7899;i Target interface.</p><p>-?<i>Adaptee (ChemicalDatabank)</i>: x?c &#273;&#7883;nh m&#7897;t interface &#273;ang t&#7891;n t&#7841;i c&#7847;n l?m t&#432;&#417;ng th?ch.</p><p>-?<i>Client (AdapterApp)</i>: ph&#7889;i h&#7907;p c?c &#273;&#7889;i t&#432;&#7907;ng ph? h&#7907;p cho Target interface.</p><p><b>Adapter: khi n?o d?ng v? d?ng &#7903; &#273;?u</b></p><p>C?c l&#7853;p tr?nh vi?n .NET vi&#7871;t c?c l&#7899;p &#273;&#432;a ra c?c ph&#432;&#417;ng th&#7913;c &#273;&#432;&#7907;c g&#7885;i b&#7903;i c?c client. H&#7847;u h&#7871;t th&#7901;i gian ch?ng c? th&#7875; &#273;i&#7873;u khi&#7875;n c?c interface, nh&#432;ng c? m&#7897;t s&#7889; tr&#432;&#7901;ng h&#7907;p, ch&#7859;ng h&#7841;n khi d?ng c?c th&#432; vi?n c&#7911;a h?ng th&#7913; 3, n&#417;i ch?ng kh?ng th&#7875; l?m &#273;&#432;&#7907;c &#273;i&#7873;u &#273;?. Th&#432; vi&#7879;n c&#7911;a h?ng th&#7913; 3 th&#7921;c thi c?c d&#7883;ch v&#7909; &#273;&#432;&#7907;c mong &#273;&#7907;i nh&#432;ng c?c ph&#432;&#417;ng th&#7913;c c&#7911;a interface v? c?c t?n c&#7911;a thu&#7897;c t?nh kh?c bi&#7879;t so v&#7899;i nh&#7919;ng g? client mong &#273;&#7907;i. &#272;?y l? m&#7897;t tr&#432;&#7901;ng h&#7907;p m? b&#7841;n c&#7847;n s&#7917; d&#7909;ng m&#7851;u thi&#7871;t k&#7871; Adapter. Adapter cung c&#7845;p m&#7897;t interface m? client mong &#273;&#7907;i s&#7917; d&#7909;ng nh&#7919;ng d&#7883;ch v&#7909; c&#7911;a m&#7897;t l&#7899;p v&#7899;i m&#7897;t interface kh?c. C?c adapter th&#432;&#7901;ng &#273;&#432;&#7907;c s&#7917; d&#7909;ng trong c?c m?i tr&#432;&#7901;ng l&#7853;p tr?nh n&#417;i m? c?c th?nh ph&#7847;n ho&#7863;c c?c &#7913;ng d&#7909;ng m&#7899;i c&#7847;n &#273;&#432;&#7907;c t?ch h&#7907;p v? l?m vi&#7879;c v&#7899;i nhau v&#7899;i c?c th?nh ph&#7847;n &#273;ang t&#7891;n t&#7841;i.</p><p>C?c adapter c&#361;ng h&#7919;u ?ch trong c?c tr&#432;&#7901;ng h&#7907;p refactor. Gi&#7843; s&#7917; b&#7841;n c? 2 l&#7899;p th&#7921;c thi c?c ch&#7913;c n&#259;ng kh?c nhau nh&#432;ng c? c?c interface kh?c nhau. Client s&#7917; d&#7909;ng c&#7843; 2 l&#7899;p n?y, nh&#432;ng ph&#7847;n code th? s&#7869; kh?ng th&#7853;t r? r?ng v? &#273;&#417;n gi&#7843;n h&#417;n &#273;&#7875; hi&#7875;u n&#7871;u ch?ng c?ng chia s&#7867; m&#7897;t interface. B&#7841;n kh?ng th&#7875; thay th&#7871; interface n?y, nh&#432;ng b&#7841;n c? th&#7875; ?che? nh&#7919;ng kh?c bi&#7879;t n?y b&#7857;ng c?ch s&#7917; d&#7909;ng Adapter cho ph?p client k&#7871;t n&#7889;i th?ng qua m&#7897;t interface chung. Adapter n?y x&#7917; l? vi&#7879;c ?nh x&#7841; gi&#7919;a interface &#273;&#432;&#7907;c chia s&#7867; v? c?c interface g&#7889;c.</p>', 99, 4),
(20, 'Which of the following options contain correct code to declare and initialize<br>variables to store whole numbers?', 5, 6),
(21, '<b>Select the options that, when inserted at // INSERT CODE HERE, will make the following code output a value of 11:</b><br><i><span style="color:rgb(0,0,255);">public class IncrementNum {<br style="color: rgb(0, 0, 255);">public static void main(String[] args) {<br style="color: rgb(0, 0, 255);">int ctr = 50;<br style="color: rgb(0, 0, 255);">// INSERT CODE HERE<br style="color: rgb(0, 0, 255);">System.out.println(ctr % 20);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</span></i>', 6, 6),
(22, '<b>What is the output of the following code?</b><br><i><span style="color:rgb(0,0,255);">int a = 10;<br style="color: rgb(0, 0, 255);">int b = 20;<br style="color: rgb(0, 0, 255);">int c = (a * (b + 2)) - 10-4 * ((2*2) - 6;<br style="color: rgb(0, 0, 255);">System.out.println(c);</span></i>', 7, 6),
(23, '<b>What is true about the following lines of code?</b><br><i><span style="color:rgb(0,0,255);">boolean b = false;<br style="color: rgb(0, 0, 255);">int i = 90;<br style="color: rgb(0, 0, 255);">System.out.println(i &gt;= b);</span></i>', 8, 6),
(24, '<b>Examine the following code and select the correct options:</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">public class Prim { // line 1<br style="color: rgb(0, 0, 255);">public static void main(String[] args) { // line 2<br style="color: rgb(0, 0, 255);">int num1 = 12; // line 3<br style="color: rgb(0, 0, 255);">float num2 = 17.8f; // line 4<br style="color: rgb(0, 0, 255);">boolean eJavaResult = true; // line 5<br style="color: rgb(0, 0, 255);">boolean returnVal = num1 &gt;= 12 &amp;&amp; num2 &lt; 4.567 // line 6<br style="color: rgb(0, 0, 255);">|| eJavaResult == true; <br style="color: rgb(0, 0, 255);">System.out.println(returnVal); // line 7<br style="color: rgb(0, 0, 255);">} // line 8<br style="color: rgb(0, 0, 255);">} // line 9</i></span><br>&nbsp;', 9, 6),
(25, '<b>If the functionality of the operators =and &gt;were to be swapped in Java (for the<br>code on line numbers 4, 5, and 6), what would be the result of the following code?</b><br>boolean myBool = false; // line 1<br><i><span style="color:rgb(0,0,255);">int yourInt = 10; // line 2<br style="color: rgb(0, 0, 255);">float hisFloat = 19.54f; // line 3<br style="color: rgb(0, 0, 255);">&nbsp;<br style="color: rgb(0, 0, 255);">&nbsp;<br style="color: rgb(0, 0, 255);">System.out.println(hisFloat &gt; yourInt); // line 4<br style="color: rgb(0, 0, 255);">System.out.println(yourInt = 10); // line 5<br style="color: rgb(0, 0, 255);">System.out.println(myBool &gt; false); // line 6</span></i>', 10, 6),
(26, '<b>How can you include encapsulation in your class design?</b><br>&nbsp;', 1, 7),
(27, '<b>Examine the following code and select the correct option(s):</b><br><i><span style="color:rgb(0,0,255);">public class Person {<br style="color: rgb(0, 0, 255);">public int height;<br style="color: rgb(0, 0, 255);">public void setHeight(int newHeight) {<br style="color: rgb(0, 0, 255);">if (newHeight &lt;= 300)<br style="color: rgb(0, 0, 255);">height = newHeight;<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</span></i>', 2, 7),
(28, 'Which&nbsp; of&nbsp; the&nbsp; following&nbsp; methods&nbsp; correctly&nbsp; accepts&nbsp; three&nbsp; whole&nbsp; numbers&nbsp; as<br>method arguments and returns their sum as a decimal number?', 3, 7),
(29, 'Which of the following statements are true?', 4, 7),
(30, '<b>Given the following definition of class Person,</b><br><i><span style="color:rgb(0,0,255);">class Person {<br style="color:rgb(0,0,255);">public String name;<br style="color:rgb(0,0,255);">public int height;<br style="color:rgb(0,0,255);">}</span></i><br><b>what is the output of the following code?</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class EJavaGuruPassObjects1 {<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">Person p = new Person();<br style="color: rgb(0, 0, 255);">p.name = "EJava";<br style="color: rgb(0, 0, 255);">anotherMethod(p);<br style="color: rgb(0, 0, 255);">System.out.println(p.name);<br style="color: rgb(0, 0, 255);">someMethod(p);<br style="color: rgb(0, 0, 255);">System.out.println(p.name);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">static void someMethod(Person p) {<br style="color: rgb(0, 0, 255);">p.name = "someMethod";<br style="color: rgb(0, 0, 255);">System.out.println(p.name);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">static void anotherMethod(Person p) {<br style="color: rgb(0, 0, 255);">p = new Person();<br style="color: rgb(0, 0, 255);">p.name = "anotherMethod";<br style="color: rgb(0, 0, 255);">System.out.println(p.name);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</i></span><br>&nbsp;', 5, 7),
(31, '<b>What is the output of the following code?</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class EJavaGuruPassPrim {<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">int ejg = 10;<br style="color: rgb(0, 0, 255);">anotherMethod(ejg);<br style="color: rgb(0, 0, 255);">System.out.println(ejg);<br style="color: rgb(0, 0, 255);">someMethod(ejg);<br style="color: rgb(0, 0, 255);">System.out.println(ejg);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">static void someMethod(int val) {<br style="color: rgb(0, 0, 255);">++val;<br style="color: rgb(0, 0, 255);">System.out.println(val);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">static void anotherMethod(int val) {<br style="color: rgb(0, 0, 255);">val = 20;<br style="color: rgb(0, 0, 255);">System.out.println(val);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</i></span><br>&nbsp;', 6, 7),
(32, 'Given the following signature of method eJava, choose the options that correctly overload this method:', 7, 7),
(33, '<b>Given the following code,</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class Course {<br style="color: rgb(0, 0, 255);">void enroll(long duration) {<br style="color: rgb(0, 0, 255);">System.out.println("long");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">void enroll(int duration) {<br style="color: rgb(0, 0, 255);">System.out.println("int");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">void enroll(String s) {<br style="color: rgb(0, 0, 255);">System.out.println("String");<br style="color: rgb(0, 0, 255);">&nbsp;<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">void enroll(Object o) {<br style="color: rgb(0, 0, 255);">System.out.println("Object");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);"></i></span><b><br>what is the output of the following code?</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class EJavaGuru {<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">Course course = new Course();<br style="color: rgb(0, 0, 255);">char c = 10;<br style="color: rgb(0, 0, 255);">course.enroll(c);<br style="color: rgb(0, 0, 255);">course.enroll("Object");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</i></span><br>', 0, 7),
(34, 'class Course {<br>void enroll(long duration) {<br>System.out.println("long");<br>}<br>void enroll(int duration) {<br>System.out.println("int");<br>}<br>void enroll(String s) {<br>System.out.println("String");<br>www.it-ebooks.info<br>171 Answers to sample exam questions<br>}<br>void enroll(Object o) {<br>System.out.println("Object");<br>}<br>}<br>what is the output of the following code?<br>class EJavaGuru {<br>public static void main(String args[]) {<br>Course course = new Course();<br>char c = 10;<br>course.enroll(c);<br>course.enroll("Object");<br>}<br>}', 8, 7),
(35, '<b>Examine the following code and select the correct options:</b><br><i><span style="color:rgb(0,0,255);">class EJava {<br style="color: rgb(0, 0, 255);">public EJava() {<br style="color: rgb(0, 0, 255);">this(7);<br style="color: rgb(0, 0, 255);">System.out.println("public");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">private EJava(int val) {<br style="color: rgb(0, 0, 255);">this("Sunday");<br style="color: rgb(0, 0, 255);">System.out.println("private");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">&nbsp;<br style="color: rgb(0, 0, 255);">&nbsp;<br style="color: rgb(0, 0, 255);">protected EJava(String val) {<br style="color: rgb(0, 0, 255);">System.out.println("protected");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">class TestEJava {<br style="color: rgb(0, 0, 255);">public static void main(String[] args) {<br style="color: rgb(0, 0, 255);">EJava eJava = new EJava();<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</span></i>', 9, 7),
(36, 'Select the incorrect options:', 10, 7),
(37, '<b>What is the output of the following code:</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class Course {<br style="color: rgb(0, 0, 255);">String courseName;<br style="color: rgb(0, 0, 255);">Course() {<br style="color: rgb(0, 0, 255);">Course c = new Course();<br style="color: rgb(0, 0, 255);">c.courseName = "Oracle";<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);"><br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">class EJavaGuruPrivate2 {<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">Course c = new Course();<br style="color: rgb(0, 0, 255);">c.courseName = "Java";<br style="color: rgb(0, 0, 255);">System.out.println(c.courseName);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</i></span><br>', 1, 11),
(38, 'Select the correct option(s):', 2, 11),
(39, '<b>Examine the following code and select the correct option(s):</b><br><i><span style="color:rgb(0,0,255);">class EJavaGuruExcep2 {<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">EJavaGuruExcep2 var = new EJavaGuruExcep2();<br style="color: rgb(0, 0, 255);">var.printArrValues(args);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">void printArrValues(String[] arr) {<br style="color: rgb(0, 0, 255);">try {<br style="color: rgb(0, 0, 255);">System.out.println(arr[0] + ":" + arr[1]);<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">catch (NullPointerException e) {<br style="color: rgb(0, 0, 255);">System.out.println("NullPointerException");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">catch (IndexOutOfBoundsException e) {<br style="color: rgb(0, 0, 255);">System.out.println("IndexOutOfBoundsException");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">catch (ArrayIndexOutOfBoundsException e) {<br style="color: rgb(0, 0, 255);">System.out.println("ArrayIndexOutOfBoundsException");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</span></i>', 3, 11),
(40, '<b>What is the output of the following code?</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class EJava {<br style="color: rgb(0, 0, 255);">void method() {<br style="color: rgb(0, 0, 255);">try {<br style="color: rgb(0, 0, 255);">guru();<br style="color: rgb(0, 0, 255);">return;<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">finally {<br style="color: rgb(0, 0, 255);">System.out.println("finally 1");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">void guru() {<br style="color: rgb(0, 0, 255);">System.out.println("guru");<br style="color: rgb(0, 0, 255);">throw new StackOverflowError();<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">EJava var = new EJava();<br style="color: rgb(0, 0, 255);">var.method();<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</i></span><br>&nbsp;', 4, 11),
(41, 'Select the incorrect statement(s):', 5, 11),
(42, 'Select the incorrect statement(s):', 6, 11),
(43, '<b>What is the output of the following code?</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class TryFinally {<br style="color: rgb(0, 0, 255);">int tryAgain() {<br style="color: rgb(0, 0, 255);">int a = 10;<br style="color: rgb(0, 0, 255);">try {<br style="color: rgb(0, 0, 255);">++a;<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">finally {<br style="color: rgb(0, 0, 255);">a++;<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">return a;<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);"><br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">System.out.println(new TryFinally().tryAgain());<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</i></span><br>', 7, 11),
(44, '<b>What is the output of the following code?</b><br><i><span style="color:rgb(0,0,255);">class EJavaBase {<br style="color: rgb(0, 0, 255);">void myMethod() throws ExceptionInInitializerError {<br style="color: rgb(0, 0, 255);">System.out.println("Base");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">class EJavaDerived extends EJavaBase {<br style="color: rgb(0, 0, 255);">void myMethod() throws RuntimeException {<br style="color: rgb(0, 0, 255);">System.out.println("Derived");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">class EJava3 {<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">EJavaBase obj = new EJavaDerived();<br style="color: rgb(0, 0, 255);">obj.myMethod();<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</span></i>', 8, 11),
(45, 'Which of the following statements are true?', 9, 11),
(46, '<b>What is the output of the following code?</b><br><span style="color:rgb(0,0,255);"><i style="color: rgb(0, 0, 255);">class EJava4 {<br style="color: rgb(0, 0, 255);">void foo() {<br style="color: rgb(0, 0, 255);">try {<br style="color: rgb(0, 0, 255);">String s = null;<br style="color: rgb(0, 0, 255);">System.out.println("1");<br style="color: rgb(0, 0, 255);">try {<br style="color: rgb(0, 0, 255);">System.out.println(s.length());<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">catch (NullPointerException e) {<br style="color: rgb(0, 0, 255);">System.out.println("inner");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">System.out.println("2");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">catch (NullPointerException e) {<br style="color: rgb(0, 0, 255);">System.out.println("outer");<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">public static void main(String args[]) {<br style="color: rgb(0, 0, 255);">EJava4 obj = new EJava4();<br style="color: rgb(0, 0, 255);">obj.foo();<br style="color: rgb(0, 0, 255);">}<br style="color: rgb(0, 0, 255);">}</i></span><br>', 10, 11),
(47, '<b>What is the output of the following code?</b><br>class Animal {<br>void jump() { System.out.println("Animal"); }<br>}<br>class Cat extends Animal {<br>void jump(int a) { System.out.println("Cat"); }<br><br>}<br>class Rabbit extends Animal {<br>void jump() { System.out.println("Rabbit"); }<br>}<br>class Circus {<br>public static void main(String args[]) {<br>Animal cat = new Cat();<br>Rabbit rabbit = new Rabbit();<br>cat.jump();<br>rabbit.jump();<br>}<br>}<br>', 1, 10),
(48, '<b>Given the following code, select the correct statements:</b><br><i>class Flower {<br>public void fragrance() {System.out.println("Flower"); }<br>}<br>class Rose {<br>public void fragrance() {System.out.println("Rose"); }<br>}<br>class Lily {<br>public void fragrance() {System.out.println("Lily"); }<br>}<br>class Bouquet {<br>public void arrangeFlowers() {<br>Flower f1 = new Rose();<br>Flower f2 = new Lily();<br>f1.fragrance();<br>}<br>}</i><br>', 2, 10),
(49, '<b>Examine the following code and select the correct method declaration to be<br>inserted at //INSERT CODE HERE:</b><br><i>interface Movable {<br>void move();<br>}<br>class Person implements Movable {<br>public void move() { System.out.println("Person move"); }<br>}<br>class Vehicle implements Movable {<br>public void move() { System.out.println("Vehicle move"); }<br>}<br>class Test {<br>// INSERT CODE HERE<br>movable.move();<br>}<br>}</i>', 3, 10),
(50, 'Select the correct statements:', 4, 10),
(51, '<b>Given the following code, select the correct statements:</b><br><i>class Person {}<br>class Employee extends Person {}<br>class Doctor extends Person {}</i>', 5, 10),
(52, 'Which of the following statements are true?', 6, 10),
(53, '<b>Given the following code, which of the options are true?</b><br><i>class Satellite {<br>void orbit() {}<br>}<br>class Moon extends Satellite {<br>void orbit() {}<br>}<br>class ArtificialSatellite extends Satellite {<br>void orbit() {}<br>}</i>', 7, 10),
(54, '<b>Examine the following code:</b><br><i>class Programmer {<br>void print() {<br>System.out.println("Programmer - Mala Gupta");<br>}<br>}<br>class Author extends Programmer {<br>void print() {<br>System.out.println("Author - Mala Gupta");<br>}<br>}<br>class TestEJava {<br>Programmer a = new Programmer();<br>// INSERT CODE HERE<br>a.print();<br>b.print();<br>}<br></i><b><br>Which of the following lines of code can be individually inserted at //INSERT&nbsp; CODE<br>HERE so that the output of the code is as follows:</b><i><br>Programmer - Mala Gupta<br>Author - Mala Gupta<br></i>', 8, 10),
(55, '<b>Given the following code, which of the options, when applied individually, will<br>make it compile successfully?</b><br><i>Line1&gt; interface Employee {}<br>Line2&gt; interface Printable extends Employee {<br>Line3&gt; String print();<br>Line4&gt; }<br>Line5&gt; class Programmer {<br>Line6&gt; String print() { return("Programmer - Mala Gupta"); }<br>Line7&gt; }<br>Line8&gt; class Author extends Programmer implements Printable, Employee {<br>Line9&gt; String print() { return("Author - Mala Gupta"); }<br>Line10&gt; }</i>', 9, 10),
(56, '<b>What is the output of the following code?</b><br><i>class Base {<br>String var = "EJava";<br>void printVar() {<br>System.out.println(var);<br>}<br>}<br>class Derived extends Base {<br>String var = "Guru";<br>void printVar() {<br>System.out.println(var);<br>}<br>}<br>class QReference {<br>public static void main(String[] args) {<br>Base base = new Base();<br>Base derived = new Derived();<br>System.out.println(base.var);<br>System.out.println(derived.var);<br>base.printVar();<br>derived.printVar();<br>}<br>}</i><br>', 10, 10),
(57, '<b>What’s the output of the following code?</b><br><i>class Loop2 {<br>public static void main(String[] args) {<br>int i = 10;<br>do<br>while (i &lt; 15)<br>i = i + 20;<br>while (i &lt; 2);<br>System.out.println(i);<br>}<br>}<br><br></i>', 1, 9),
(58, '<b>What’s the output of the following code?</b><br>class Loop2 {<br>public static void main(String[] args) {<br>int i = 10;<br>do<br>while (i++ &lt; 15)<br>i = i + 20;<br>while (i &lt; 2);<br>System.out.println(i);<br>}<br>}<br><br>', 2, 9),
(59, 'Which of the following statements is true?', 3, 9),
(60, '<b>What’s the output of the following code?</b><br><i>int a = 10;<br>if (a++ &gt; 10) {<br>System.out.println("true");<br>}<br>{<br>System.out.println("false");<br>}<br>System.out.println("ABC");</i>', 4, 9),
(61, '<b>Given the following code, which of the following lines of code can individually<br>replace the //INSERT CODE HEREline so that the code compiles successfully?</b><br><i>class EJavaGuru {<br>public static int getVal() {<br>return 100;<br>}<br>public static void main(String args[]) {<br>int num = 10;<br>final int num2 = 20;<br>switch (num) {<br>// INSERT CODE HERE<br>break;<br>default: System.out.println("default");<br>}<br>}<br>}</i>', 5, 9),
(62, '<b>What’s the output of the following code?</b><br><i>class EJavaGuru {<br>public static void main(String args[]) {<br>int num = 20;<br>final int num2;<br>num2 = 20;<br><br>switch (num) {<br>default: System.out.println("default");<br>case num2: System.out.println(4);<br>break;<br>}<br>}<br>}</i><br>', 6, 9),
(63, '<b>What’s the output of the following code?</b><br><i>class EJavaGuru {<br>public static void main(String args[]) {<br>int num = 120;<br>switch (num) {<br>default: System.out.println("default");<br>case 0: System.out.println("case1");<br>case 10*2-20: System.out.println("case2");<br>break;<br>}<br>}<br>}</i>', 7, 9),
(64, '<b>What’s the output of the following code?</b><br><i>class EJavaGuru3 {<br>public static void main(String args[]) {<br>byte foo = 120;<br>switch (foo) {<br>default: System.out.println("ejavaguru"); break;<br>case 2: System.out.println("e"); break;<br>case 120: System.out.println("ejava");<br>case 121: System.out.println("enum");<br>case 127: System.out.println("guru"); break;<br>}<br>}<br>}</i>', 8, 9),
(65, '<b>What’s the output of the following code?</b><br><i>class EJavaGuru4 {<br>public static void main(String args[]) {<br>boolean myVal = false;<br>if (myVal=true)<br>for (int i = 0; i &lt; 2; i++) System.out.println(i);<br>&nbsp;<br>else System.out.println("else");<br>}<br>}</i>', 9, 9),
(66, '<b>What’s the output of the following code?</b><br><i>class EJavaGuru5 {<br>public static void main(String args[]) {<br>int i = 0;<br>for (; i &lt; 2; i=i+5) {<br>if (i &lt; 5) continue;<br>System.out.println(i);<br>}<br>System.out.println(i);<br>}<br>}</i>', 10, 9),
(67, 'Which&nbsp; of&nbsp; the&nbsp; following&nbsp; are&nbsp; valid&nbsp; lines&nbsp; of&nbsp; code&nbsp; to&nbsp; define&nbsp; a&nbsp; multidimensional<br>intarray?', 3, 8),
(68, 'Which of the following statements are correct?', 4, 8),
(69, 'Which of the following statements are correct?', 5, 8),
(70, '<b>What is the output of the following code?</b><br><i>class EJavaGuruString {<br>public static void main(String args[]) {<br>String ejg1 = new String("E Java");<br>String ejg2 = new String("E Java");<br>String ejg3 = "E Java";<br>String ejg4 = "E Java";<br>do<br>System.out.println(ejg1.equals(ejg2));<br>while (ejg3 == ejg4);<br>}<br>}</i><br>', 7, 8),
(72, '<p>Nh&#7919;ng l&#7899;p v?/ho&#7863;c &#273;&#7889;i t&#432;&#7907;ng trong m&#7851;u thi&#7871;t k&#7871; n?y:</p><p>- <i>Target (ChemicalCompound)</i>: &#273;&#7883;nh ngh&#297;a m&#7897;t domain-specific interface m? Client s&#7917; d&#7909;ng.</p><p>- <i>Adapter (Compound)</i>: l?m cho Adaptee interface t&#432;&#417;ng th?ch v&#7899;i Target interface.</p><p>- <i>Adaptee (ChemicalDatabank)</i>: x?c &#273;&#7883;nh m&#7897;t interface &#273;ang t&#7891;n t&#7841;i c&#7847;n l?m t&#432;&#417;ng th?ch.</p><p>- <i>Client (AdapterApp)</i>: ph&#7889;i h&#7907;p c?c &#273;&#7889;i t&#432;&#7907;ng ph? h&#7907;p cho Target interface.</p><p><b>Adapter: khi n?o d?ng v? d?ng &#7903; &#273;?u</b></p><p>C?c l&#7853;p tr?nh vi?n .NET vi&#7871;t c?c l&#7899;p &#273;&#432;a ra c?c ph&#432;&#417;ng th&#7913;c &#273;&#432;&#7907;c g&#7885;i b&#7903;i c?c client. H&#7847;u h&#7871;t th&#7901;i gian ch?ng c? th&#7875; &#273;i&#7873;u khi&#7875;n c?c interface, nh&#432;ng c? m&#7897;t s&#7889; tr&#432;&#7901;ng h&#7907;p, ch&#7859;ng h&#7841;n khi d?ng c?c th&#432; vi?n c&#7911;a h?ng th&#7913; 3, n&#417;i ch?ng kh?ng th&#7875; l?m &#273;&#432;&#7907;c &#273;i&#7873;u &#273;?. Th&#432; vi&#7879;n c&#7911;a h?ng th&#7913; 3 th&#7921;c thi c?c d&#7883;ch v&#7909; &#273;&#432;&#7907;c mong &#273;&#7907;i nh&#432;ng c?c ph&#432;&#417;ng th&#7913;c c&#7911;a interface v? c?c t?n c&#7911;a thu&#7897;c t?nh kh?c bi&#7879;t so v&#7899;i nh&#7919;ng g? client mong &#273;&#7907;i. &#272;?y l? m&#7897;t tr&#432;&#7901;ng h&#7907;p m? b&#7841;n c&#7847;n s&#7917; d&#7909;ng m&#7851;u thi&#7871;t k&#7871; Adapter. Adapter cung c&#7845;p m&#7897;t interface m? client mong &#273;&#7907;i s&#7917; d&#7909;ng nh&#7919;ng d&#7883;ch v&#7909; c&#7911;a m&#7897;t l&#7899;p v&#7899;i m&#7897;t interface kh?c. C?c adapter th&#432;&#7901;ng &#273;&#432;&#7907;c s&#7917; d&#7909;ng trong c?c m?i tr&#432;&#7901;ng l&#7853;p tr?nh n&#417;i m? c?c th?nh ph&#7847;n ho&#7863;c c?c &#7913;ng d&#7909;ng m&#7899;i c&#7847;n &#273;&#432;&#7907;c t?ch h&#7907;p v? l?m vi&#7879;c v&#7899;i nhau v&#7899;i c?c th?nh ph&#7847;n &#273;ang t&#7891;n t&#7841;i.</p><p>C?c adapter c&#361;ng h&#7919;u ?ch trong c?c tr&#432;&#7901;ng h&#7907;p refactor. Gi&#7843; s&#7917; b&#7841;n c? 2 l&#7899;p th&#7921;c thi c?c ch&#7913;c n&#259;ng kh?c nhau nh&#432;ng c? c?c interface kh?c nhau. Client s&#7917; d&#7909;ng c&#7843; 2 l&#7899;p n?y, nh&#432;ng ph&#7847;n code th? s&#7869; kh?ng th&#7853;t r? r?ng v? &#273;&#417;n gi&#7843;n h&#417;n &#273;&#7875; hi&#7875;u n&#7871;u ch?ng c?ng chia s&#7867; m&#7897;t interface. B&#7841;n kh?ng th&#7875; thay th&#7871; interface n?y, nh&#432;ng b&#7841;n c? th&#7875; ?che? nh&#7919;ng kh?c bi&#7879;t n?y b&#7857;ng c?ch s&#7917; d&#7909;ng Adapter cho ph?p client k&#7871;t n&#7889;i th?ng qua m&#7897;t interface chung. Adapter n?y x&#7917; l? vi&#7879;c ?nh x&#7841; gi&#7919;a interface &#273;&#432;&#7907;c chia s&#7867; v? c?c interface g&#7889;c.</p>', 88, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tblsections`
--

CREATE TABLE IF NOT EXISTS `tblsections` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL DEFAULT 'No name',
  `Order` tinyint(1) NOT NULL DEFAULT '0',
  `CategoryID` tinyint(4) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_category_section` (`CategoryID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `tblsections`
--

INSERT INTO `tblsections` (`ID`, `Name`, `Order`, `CategoryID`) VALUES
(4, 'Full mock exam', 8, 1),
(5, 'Java basics', 1, 1),
(6, 'Working with Java data types', 2, 1),
(7, 'Methods and encapsulation', 3, 1),
(8, 'String, StringBuilder, Arrays, and ArrayList', 4, 1),
(9, 'Flow control', 5, 1),
(10, 'Working with inheritance', 6, 1),
(11, 'Exception handling', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblusers`
--

CREATE TABLE IF NOT EXISTS `tblusers` (
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `FullName` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblusers`
--

INSERT INTO `tblusers` (`UserName`, `Password`, `FullName`, `Email`, `IsActive`) VALUES
('admin', '16ff531df76765f8d1c7bd3c2dbd8508', NULL, NULL, NULL),
('khanhpham', 'bbb342d0df6afa927cc873e08d9b3a4e', NULL, NULL, NULL),
('trule', 'bbb342d0df6afa927cc873e08d9b3a4e', NULL, NULL, NULL),
('tungo', '16ff531df76765f8d1c7bd3c2dbd8508', NULL, NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
