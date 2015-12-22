package com.sias.znwy.web.util;

import com.sias.znwy.web.util.CustomRequest.OnRespListener;



/**
 * ���������
 * 
 * @author zzh
 * 
 */
public interface IQuery {
	
	public final static int ADDTYPE  = 1;//����
	public final static int MODTYPE  = 2;//�޸�
	public final static int DELTYPE  = 3;//ɾ��

	/**
	 * ��Map��ֵ�Ե���ʽ�ύ����
	 * 
	 * @param cmd
	 *            ����ֵ
	 * @param map
	 *            Map��ֵ��
	 * @param listener
	 *            ��������Ӧ������
	 * @return
	 */
	public IReq query( IWebKVParam param, OnRespListener listener);
}