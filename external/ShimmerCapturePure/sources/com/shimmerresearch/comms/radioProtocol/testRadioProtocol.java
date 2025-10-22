package com.shimmerresearch.comms.radioProtocol;

import com.google.protobuf.Descriptors;
import com.shimmerresearch.comms.radioProtocol.ShimmerLiteProtocolInstructionSet;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class testRadioProtocol {
    public static void main(String[] strArr) {
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.getDescriptor().getValues());
        String[] strArr2 = new String[ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.getDescriptor().getValues().size()];
        Iterator it2 = ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.getDescriptor().getValues().iterator();
        int i = 0;
        while (it2.hasNext()) {
            strArr2[i] = ((Descriptors.EnumValueDescriptor) it2.next()).getName();
            i++;
        }
        System.out.println(strArr2);
        System.out.println(255);
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.valueOf(255).name());
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.valueOf(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.ACK_COMMAND_PROCESSED.toString()).getNumber());
        if (ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.valueOf(254) != null) {
            System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.valueOf(254).name());
        }
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsSet.ACK_COMMAND_PROCESSED.toString());
        System.out.println(142);
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_INFOMEM_COMMAND.toString());
        System.out.println(141);
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsResponse.INFOMEM_RESPONSE.toString());
        ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_SAMPLING_RATE_COMMAND.getDescriptorForType().getValues();
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_BAUD_RATE_COMMAND.getValueDescriptor().getOptions());
        ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_SAMPLING_RATE_COMMAND.getValueDescriptor().getOptions().getAllFields();
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().findFieldByName("response_size"));
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_SAMPLING_RATE_COMMAND.getValueDescriptor().getOptions());
        System.out.println(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_SAMPLING_RATE_COMMAND.getValueDescriptor().getOptions().getField(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().findFieldByName("response_size")));
        System.out.println(((Integer) ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.GET_SAMPLING_RATE_COMMAND.getValueDescriptor().getOptions().getField(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().findFieldByName("response_size"))).intValue());
        int iIntValue = ((Integer) ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsGet.valueOf(142).getValueDescriptor().getOptions().getField(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().findFieldByName("response_size"))).intValue();
        System.out.println(StringUtils.SPACE + iIntValue);
        int iIntValue2 = ((Integer) ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.InstructionsResponse.INFOMEM_RESPONSE.getValueDescriptor().getOptions().getField(ShimmerLiteProtocolInstructionSet.LiteProtocolInstructionSet.getDescriptor().findFieldByName("response_size"))).intValue();
        System.out.println(StringUtils.SPACE + iIntValue2);
    }
}
