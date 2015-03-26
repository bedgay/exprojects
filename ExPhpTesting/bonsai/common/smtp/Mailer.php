<?php
require_once 'extlib/PHPMailer_v5.1/class.phpmailer.php';
require_once 'extlib/PHPMailer_v5.1/class.smtp.php';
/**
 * Send mail
 * @author Anh Tu Ngo
 * @date 12/06/2011
 */
final class Mailer {
	private static $instance = null;
	private $mailer;
	
	// Singleton constructor
	private function __construct() { 
		$mail = new PHPMailer();
		$mail->IsSMTP(); // set mailer to use SMTP
		$mail->Host = "smtp.gmail.com"; // specify main and backup server
		$mail->Port = 465; // set the port to use
		$mail->SMTPAuth = true; // turn on SMTP authentication
		$mail->SMTPSecure = 'ssl';
		$mail->Username = "free@bonphuong.info"; // your SMTP username or your gmail username
		$mail->Password = "freePass"; // your SMTP password or your gmail password
		$mail->From = "free@bonphuong.info";
		$mail->FromName = "Web Master"; // Name to indicate where the email came from when the recepient received
		$mail->AddReplyTo($mail->From, $mail->FromName);
		$mail->WordWrap = 50; // set word wrap
		$mail->IsHTML(true); // send as HTML
		
		$this->mailer = $mail;
	}
	
	/**
	 * GoogleSmtp instance only
	 * @return Mailer
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new Mailer();
		return self::$instance;
	}
	
	/**
	 * Creates message and assigns Mailer.
	 * @param string $subject
	 * @param string $body
	 * @param string $receiveAddress
	 * @param string $receiveName
	 * @param string $altBody
	 * @return boolean
	 */
	public function send($subject, $body, $receiveAddress, $receiveName = '') {
				
		$this->mailer->Subject	= $subject;
		$this->mailer->Body		= $body;
		$this->mailer->AddAddress($receiveAddress, $receiveName);
		
		return $this->mailer->Send();
	}
	
}
?>
