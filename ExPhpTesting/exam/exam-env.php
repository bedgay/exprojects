<?php
$CONFIG_ENV = new Object();
//$CONFIG_ENV->CPANEL_INDEX = '?c=cpanel&a=cats';
?>
<?php // CPancel Controller
$CONFIG_ENV->cpanel = new Object();
$CONFIG_ENV->cpanel->CLASS_NAME = 'CPanelController';

$CONFIG_ENV->cpanel->cats = new Object();
$CONFIG_ENV->cpanel->cats->METHOD = 'categories';

$CONFIG_ENV->cpanel->catnew = new Object();
$CONFIG_ENV->cpanel->catnew->METHOD = 'catnew';

$CONFIG_ENV->cpanel->catdel = new Object();
$CONFIG_ENV->cpanel->catdel->METHOD = 'catdel';

$CONFIG_ENV->cpanel->catedit = new Object();
$CONFIG_ENV->cpanel->catedit->METHOD = 'catedit';

$CONFIG_ENV->cpanel->secs = new Object();
$CONFIG_ENV->cpanel->secs->METHOD = 'secs';

$CONFIG_ENV->cpanel->secnew = new Object();
$CONFIG_ENV->cpanel->secnew->METHOD = 'secnew';

$CONFIG_ENV->cpanel->secedit = new Object();
$CONFIG_ENV->cpanel->secedit->METHOD = 'secedit';

$CONFIG_ENV->cpanel->secdel = new Object();
$CONFIG_ENV->cpanel->secdel->METHOD = 'secdel';

$CONFIG_ENV->cpanel->ques = new Object();
$CONFIG_ENV->cpanel->ques->METHOD = 'ques';

$CONFIG_ENV->cpanel->quenew = new Object();
$CONFIG_ENV->cpanel->quenew->METHOD = 'quenew';

$CONFIG_ENV->cpanel->queedit = new Object();
$CONFIG_ENV->cpanel->queedit->METHOD = 'queedit';

$CONFIG_ENV->cpanel->secdel = new Object();
$CONFIG_ENV->cpanel->secdel->METHOD = 'secdel';

$CONFIG_ENV->cpanel->quedel = new Object();
$CONFIG_ENV->cpanel->quedel->METHOD = 'quedel';
?>
<?php // User Controller
$CONFIG_ENV->user = new Object();
$CONFIG_ENV->user->CLASS_NAME = 'UserController';

$CONFIG_ENV->user->security = new Object();
$CONFIG_ENV->user->security->METHOD = 'security';

$CONFIG_ENV->user->login = new Object();
$CONFIG_ENV->user->login->METHOD = 'login';

$CONFIG_ENV->user->logout = new Object();
$CONFIG_ENV->user->logout->METHOD = 'logout';
?>
<?php // Exam Controller
$CONFIG_ENV->exam = new Object();
$CONFIG_ENV->exam->CLASS_NAME = 'ExamController';

$CONFIG_ENV->exam->cats = new Object();
$CONFIG_ENV->exam->cats->METHOD = 'categories';

$CONFIG_ENV->exam->exam = new Object();
$CONFIG_ENV->exam->exam->METHOD = 'exam';

$CONFIG_ENV->exam->testing = new Object();
$CONFIG_ENV->exam->testing->METHOD = 'testing';

$CONFIG_ENV->exam->result = new Object();
$CONFIG_ENV->exam->result->METHOD = 'result';
?>