ʵ���塢���̻���ʵ���ʾ������ �޸Ĳ��԰�
2021.5.13
###### �д����ԣ��ر�����δ�����Ƿ�д�����ȣ��Լ��Ƿ�û�м��� ######


��Ҫ�޸�֮����


1. ����msgrcv��msgsnd������Ϣ���Ȳ������޸�
���磺
msgsnd(quest_id,&msg_arg,sizeof(msg_arg),0);
��Ϊ��
msgsnd(quest_id, &msg_arg, sizeof(msg_arg.mid), 0);

msgrcv��Ҳ���ơ�


2. control.c����ѭ���߼��������⣬�޸�֮��


3. Ϊreader��writer���������е�2������������ģ�����ζ�������д֮���ʱ����


4. ԭreader.c��ѭ�����һ����䣬msgsnd�����һ��������bug��
msgsnd(quest_id,&msg_arg,sizeof(msg_arg),quest_flg);
��Ϊ��
msgsnd(quest_id, &msg_arg, sizeof(msg_arg.mid), 0);


5. ����΢С���޸�
����ɾ��δʹ�õı����ȡ�
