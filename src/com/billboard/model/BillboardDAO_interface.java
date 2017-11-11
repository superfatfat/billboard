package com.billboard.model;

import java.util.*;

public interface BillboardDAO_interface {
          public void insert(BillboardVO billboardVO);
          public void update(BillboardVO billboardVO);
          public void delete(Integer no);
          public BillboardVO findByPrimaryKey(Integer no);
          public List<BillboardVO> getAll();
          public List<BillboardVO> getAll(int firstResult, int maxResults);
}
