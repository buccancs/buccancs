package org.apache.commons.math3.exception.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class ExceptionContext implements Serializable {
    private static final long serialVersionUID = -6024911025449780478L;
    private Throwable throwable;
    private List<Localizable> msgPatterns = new ArrayList();
    private List<Object[]> msgArguments = new ArrayList();
    private Map<String, Object> context = new HashMap();

    public ExceptionContext(Throwable th) {
        this.throwable = th;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void addMessage(Localizable localizable, Object... objArr) {
        this.msgPatterns.add(localizable);
        this.msgArguments.add(ArgUtils.flatten(objArr));
    }

    public void setValue(String str, Object obj) {
        this.context.put(str, obj);
    }

    public Object getValue(String str) {
        return this.context.get(str);
    }

    public Set<String> getKeys() {
        return this.context.keySet();
    }

    public String getMessage() {
        return getMessage(Locale.US);
    }

    public String getLocalizedMessage() {
        return getMessage(Locale.getDefault());
    }

    public String getMessage(Locale locale) {
        return buildMessage(locale, ": ");
    }

    public String getMessage(Locale locale, String str) {
        return buildMessage(locale, str);
    }

    private String buildMessage(Locale locale, String str) {
        StringBuilder sb = new StringBuilder();
        int size = this.msgPatterns.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Localizable localizable = this.msgPatterns.get(i2);
            sb.append(new MessageFormat(localizable.getLocalizedString(locale), locale).format(this.msgArguments.get(i2)));
            i++;
            if (i < size) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.throwable);
        serializeMessages(objectOutputStream);
        serializeContext(objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.throwable = (Throwable) objectInputStream.readObject();
        deSerializeMessages(objectInputStream);
        deSerializeContext(objectInputStream);
    }

    private void serializeMessages(ObjectOutputStream objectOutputStream) throws IOException {
        int size = this.msgPatterns.size();
        objectOutputStream.writeInt(size);
        for (int i = 0; i < size; i++) {
            objectOutputStream.writeObject(this.msgPatterns.get(i));
            Object[] objArr = this.msgArguments.get(i);
            objectOutputStream.writeInt(objArr.length);
            for (Object obj : objArr) {
                if (obj instanceof Serializable) {
                    objectOutputStream.writeObject(obj);
                } else {
                    objectOutputStream.writeObject(nonSerializableReplacement(obj));
                }
            }
        }
    }

    private void deSerializeMessages(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int i = objectInputStream.readInt();
        this.msgPatterns = new ArrayList(i);
        this.msgArguments = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.msgPatterns.add((Localizable) objectInputStream.readObject());
            int i3 = objectInputStream.readInt();
            Object[] objArr = new Object[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                objArr[i4] = objectInputStream.readObject();
            }
            this.msgArguments.add(objArr);
        }
    }

    private void serializeContext(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.context.size());
        for (Map.Entry<String, Object> entry : this.context.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            Object value = entry.getValue();
            if (value instanceof Serializable) {
                objectOutputStream.writeObject(value);
            } else {
                objectOutputStream.writeObject(nonSerializableReplacement(value));
            }
        }
    }

    private void deSerializeContext(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int i = objectInputStream.readInt();
        this.context = new HashMap();
        for (int i2 = 0; i2 < i; i2++) {
            this.context.put((String) objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    private String nonSerializableReplacement(Object obj) {
        return "[Object could not be serialized: " + obj.getClass().getName() + "]";
    }
}
