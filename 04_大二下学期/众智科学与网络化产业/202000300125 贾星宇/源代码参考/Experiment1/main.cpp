



/*
 *
���룺���������ͼ
�����
1��ÿ���ڵ�ľۼ�ϵ��
2��ÿ���ڵ�Ե������ص���

��ض��壺
�ۼ�ϵ�����ڵ�A�ľۼ�ϵ�� = A��������������֮��Ҳ�����ѵĸ��ʣ����ھӼ����ѶԵĸ��������ܶ�����
�����ص��ȣ���A��B��Ϊ�ھӵĽڵ���/ ��ڵ�A��B������һ��Ϊ�ھӵĽڵ���

 */

#include <iostream>
using namespace std;

int* findFriends(int ** graph,int mine,int len){
    int  *result = new int[len];
    int count = 0;
    for (int i = 0; i < len; ++i)
        if (graph[i][mine]==1)
            result[count++] = i;

    for (int i = count; i < len; ++i) {
        result[i] = -1;
    }

    return result;
}
//������ϣ���n��������m��
int C(int m,int n){
    int a = 1;
    for (int i = 0; i < m; ++i) {
        a *= (n-i);
    }
    return a/m;
}
//�õ����м�������
int getTheLength(int * arr,int length){
    int result = 0;
    for (int i = 0; i < length; ++i) {
        if (arr[i]!=-1)result++;
    }
    return result;
}
//�õ����������л�Ϊ���ѵĸ���
int getBeFriends(int *friends,int len,int **graph){
    int result = 0;
    for (int i = 0; i < len; ++i) {
        for (int j = 0; j < len; ++j) {
            if (graph[friends[i]][friends[j]]==1)result++;
        }
    }
    return result/2;
}

//���㹲ͬ����
int* getSameFriends(int a,int b,int ** graph,int length){
    int * sameFriends = new int[length];
    int count = 0;
    for (int i = 0; i < length; ++i) {
        if (graph[a][i]==1&&graph[b][i]==1) sameFriends[count++] = i;
    }
    for (int i = count; i < length; ++i) {
        sameFriends[i] = -1;
    }
    return sameFriends;
}
//�����ܹ�����
int* getBothFriends(int a,int b,int ** graph,int length){
    int * sameFriends = new int[length];
    int count = 0;
    for (int i = 0; i < length; ++i) {
        if (graph[a][i]==1||graph[b][i]==1)
            if (i!=a && i!= b)sameFriends[count++] = i;
    }
    for (int i = count; i < length; ++i) {
        sameFriends[i] = -1;
    }
    return sameFriends;
}






int main() {
    //�����ڽӾ���洢����ͼ
    //4
    //0 1 1 1 1 0 0 1 1 0 0 0 1 1 0 0
    //5
    //0 1 1 1 0 1 0 1 0 0 1 1 0 0 1 1 0 0 0 1 0 0 1 1 0
    int length;
    cin >> length;
    int c2l = C(2,length);
    int ** graphMartix = new int*[length];
    int ** friends = new int*[length];
    int ** sameFriends = new int *[2];
    int ** bothFriends = new int *[2];
    double * clusteringCoefficient = new double[length];//ÿ���ڵ�ľۼ�ϵ��
    double * neighborhoodOverlap = new double[C(2,length)];//�ڵ�Ե������ص���


    for (int i = 0; i < length; ++i) {
        graphMartix[i] = new int[length];
        friends[i] = new int[length];
        sameFriends[i] = new int[c2l];
        bothFriends[i] = new int[c2l];
    }


//����ͼ
    for (int i = 0; i < length; ++i) {
        for (int j = 0; j < length; ++j) {
            cin >> graphMartix[i][j];
        }
    }
    //������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������

    //�õ�������˭
    for (int i = 0; i < length; ++i)
        friends[i] = findFriends(graphMar5tix,i,length);

//    for (int i = 0; i < length; ++i) {
//        for (int j = 0; j < length; ++j) {
//            cout << friends[i][j]<<',';
//        }
//        cout << endl;
//    }

    //����ڵ�ۼ�ϵ��
    int tlen = length;
    for (int i = 0; i < tlen; ++i) {
        if (getTheLength(friends[i],length)<2)
            clusteringCoefficient[i] = 0;
        else clusteringCoefficient[i] =
                ((double)getBeFriends(friends[i], getTheLength(friends[i],length),graphMartix)
                /
                (double)C(2,getTheLength(friends[i],length)));
    }
    //��ӡ��
    for (int i = 0; i < length; ++i)
        cout <<"node "<<i<<"'s clustering coefficient is "<<clusteringCoefficient[i]<<endl;

    //ÿ�Խڵ�������ص���
    int countB = 0;
    int countS = 0;
    int countN = 0;
    for (int i = 0; i < length; ++i) {
        for (int j = i+1; j < length; ++j) {
            sameFriends[countS++] = getSameFriends(i,j,graphMartix,c2l);
            bothFriends[countB++] = getBothFriends(i,j,graphMartix,c2l);

            cout << "the friend are both their friends:";
            for (int k = 0; k < c2l; ++k) {
                cout << sameFriends[countS-1][k]<<",";
            }
            cout << "     the friends are their friends:";
            for (int k = 0; k < c2l; ++k) {
                cout << bothFriends[countB-1][k]<<",";
            }
            cout<<endl;
            neighborhoodOverlap[countN++] =
                    (double)getTheLength(sameFriends[countS-1],c2l)/(double) getTheLength(bothFriends[countB-1],c2l);

            cout << "node "<<i<<" and node "<<j<<"'s neighborhood overlap is "<<neighborhoodOverlap[countN-1]<<endl;
            cout << endl;

        }
    }


    return 0;
}
