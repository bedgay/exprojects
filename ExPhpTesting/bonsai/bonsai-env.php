<?php
/**
 * Include for [common] backage
 */
$PATH = 'bonsai/common';
require_once "$PATH/annotation/Annotation.php";
require_once "$PATH/annotation/model/ModelAnnotation.php";
require_once "$PATH/http/HTTP.php";
require_once "$PATH/http/Html.php";
require_once "$PATH/util/Object.php";
require_once "$PATH/util/String.php";
require_once "$PATH/util/Date.php";
/**
 * Include for [model] backage
 */
$PATH = 'bonsai/model';
require_once "$PATH/dao/DAO.php";
require_once "$PATH/entity/Entity.php";

/**
 * Include for [controller] backage
 */
$PATH = 'bonsai/controller';
require_once "$PATH/Controller.php";
require_once "$PATH/Dispatcher.php";
?>