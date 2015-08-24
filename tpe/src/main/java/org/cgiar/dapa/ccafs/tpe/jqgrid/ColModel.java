/*****************************************************************
 * This file is part of CCAFS Target Population Environments Identification Platform.
 * CCAFS TPE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS TPE Identification Platform is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS TPE Identification Platform. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.dapa.ccafs.tpe.jqgrid;

public class ColModel {
	private String name;
	private String index;
	private Integer width;
	private String align;// left, center or right
	private boolean editable;// true or false
	private boolean hidden;// false or true
	private boolean sortable;// false or true
	private boolean key;// true or false
	private boolean resizable;// true or false
	private boolean search;// false or true

	public ColModel(String name, String index, Integer width, String align,
			boolean editable, boolean sortable, boolean key, boolean resizable,
			boolean search) {
		super();
		this.name = name;
		this.index = index;
		this.width = width;
		this.align = align;
		this.editable = editable;
		this.sortable = sortable;
		this.key = key;
		this.resizable = resizable;
		this.search = search;
	}

	public ColModel(String name, String index, Integer width, String align,
			boolean editable, boolean sortable, boolean resizable,
			boolean search) {
		super();
		this.name = name;
		this.index = index;
		this.width = width;
		this.align = align;
		this.editable = editable;
		this.sortable = sortable;
		this.resizable = resizable;
		this.search = search;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

}
