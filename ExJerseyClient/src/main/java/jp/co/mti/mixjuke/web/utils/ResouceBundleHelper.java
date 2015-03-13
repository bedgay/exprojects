/**
 * 
 */
package jp.co.mti.mixjuke.web.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Xuan Nguyen
 * 
 */
@Component
public class ResouceBundleHelper implements MessageSourceAware {

    private static MessageSource messageSource;

    /**
     * @return the messageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Get I18N message.
     * 
     * @param code
     *            I18N code.
     * @param params
     *            I18N parameters.
     * @return the right message base on I18N code.
     */
    public static String getMessage(String code, Object... params) {
        return messageSource.getMessage(code, params,
                LocaleContextHolder.getLocale());
    }

    @SuppressWarnings("static-access")
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
