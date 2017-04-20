/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import com.base.TreeNode;
/*   4:    */ import com.sys.bean.SysMenu;
/*   5:    */ import com.sys.bean.SysMenuBtn;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.List;
/*   8:    */ import java.util.Map;
/*   9:    */ 
/*  10:    */ public class TreeUtil
/*  11:    */ {
/*  12:    */   private static final String MENU_ID = "menu_";
/*  13:    */   private static final String BTN_ID = "btn_";
/*  14:    */   List<SysMenu> rootMenus;
/*  15:    */   List<SysMenu> childMenus;
/*  16:    */   List<SysMenuBtn> childBtns;
/*  17:    */   
/*  18:    */   public TreeUtil(List<SysMenu> rootMenus, List<SysMenu> childMenus)
/*  19:    */   {
/*  20: 21 */     this.rootMenus = rootMenus;
/*  21: 22 */     this.childMenus = childMenus;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public TreeUtil(List<SysMenu> rootMenus, List<SysMenu> childMenus, List<SysMenuBtn> childBtns)
/*  25:    */   {
/*  26: 26 */     this.rootMenus = rootMenus;
/*  27: 27 */     this.childMenus = childMenus;
/*  28: 28 */     this.childBtns = childBtns;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public List<TreeNode> getTreeNode()
/*  32:    */   {
/*  33: 32 */     return getRootNodes();
/*  34:    */   }
/*  35:    */   
/*  36:    */   private TreeNode MenuToNode(SysMenu menu)
/*  37:    */   {
/*  38: 41 */     if (menu == null) {
/*  39: 42 */       return null;
/*  40:    */     }
/*  41: 44 */     TreeNode node = new TreeNode();
/*  42: 45 */     node.setId("menu_" + menu.getId());
/*  43: 46 */     node.setDataId(menu.getId());
/*  44: 47 */     node.setText(menu.getName());
/*  45: 48 */     node.setUrl(menu.getUrl());
/*  46: 49 */     node.setParentId(menu.getParentId());
/*  47: 50 */     node.getAttributes().put("type", "0");
/*  48: 51 */     node.getAttributes().put("id", menu.getId());
/*  49: 52 */     return node;
/*  50:    */   }
/*  51:    */   
/*  52:    */   private TreeNode BtnToNode(SysMenuBtn btn)
/*  53:    */   {
/*  54: 62 */     if (btn == null) {
/*  55: 63 */       return null;
/*  56:    */     }
/*  57: 65 */     TreeNode node = new TreeNode();
/*  58: 66 */     node.setId("btn_" + btn.getId());
/*  59: 67 */     node.setDataId(btn.getId());
/*  60: 68 */     node.setText(btn.getBtnName());
/*  61: 69 */     node.setParentId(btn.getMenuid());
/*  62: 70 */     node.getAttributes().put("type", "1");
/*  63: 71 */     node.getAttributes().put("id", btn.getId());
/*  64: 72 */     return node;
/*  65:    */   }
/*  66:    */   
/*  67:    */   private List<TreeNode> getRootNodes()
/*  68:    */   {
/*  69: 76 */     List<TreeNode> rootNodes = new ArrayList();
/*  70: 77 */     for (SysMenu menu : this.rootMenus)
/*  71:    */     {
/*  72: 78 */       TreeNode node = MenuToNode(menu);
/*  73: 79 */       if (node != null)
/*  74:    */       {
/*  75: 80 */         addChlidNodes(node);
/*  76: 81 */         rootNodes.add(node);
/*  77:    */       }
/*  78:    */     }
/*  79: 84 */     return rootNodes;
/*  80:    */   }
/*  81:    */   
/*  82:    */   private void addChlidNodes(TreeNode rootNode)
/*  83:    */   {
/*  84: 93 */     List<TreeNode> childNodes = new ArrayList();
/*  85: 94 */     for (SysMenu menu : this.childMenus) {
/*  86: 95 */       if (rootNode.getDataId().equals(menu.getParentId()))
/*  87:    */       {
/*  88: 96 */         TreeNode node = MenuToNode(menu);
/*  89: 97 */         if ((this.childBtns != null) && (!this.childBtns.isEmpty())) {
/*  90: 98 */           addChlidBtn(node);
/*  91:    */         }
/*  92:100 */         childNodes.add(node);
/*  93:    */       }
/*  94:    */     }
/*  95:103 */     rootNode.setChildren(childNodes);
/*  96:    */   }
/*  97:    */   
/*  98:    */   private void addChlidBtn(TreeNode treeNode)
/*  99:    */   {
/* 100:113 */     List<TreeNode> childNodes = new ArrayList();
/* 101:114 */     for (SysMenuBtn btn : this.childBtns) {
/* 102:115 */       if (treeNode.getDataId().equals(btn.getMenuid()))
/* 103:    */       {
/* 104:116 */         TreeNode node = BtnToNode(btn);
/* 105:117 */         childNodes.add(node);
/* 106:    */       }
/* 107:    */     }
/* 108:120 */     treeNode.setChildren(childNodes);
/* 109:    */   }
/* 110:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.TreeUtil
 * JD-Core Version:    0.7.0.1
 */