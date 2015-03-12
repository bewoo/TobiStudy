package learning.factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Woo on 2015-03-12.
 */
public class MessageFactoryBean implements FactoryBean<Message>{
    String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<? extends Message> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
