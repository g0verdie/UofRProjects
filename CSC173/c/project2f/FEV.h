#ifndef _FEV_h
#define _FEV_h

extern void dvec_free();
extern matrix_t mat_new();
extern int dvec_pr();
extern void mat_free();
extern void cberr();


/***************external FEV**************/

extern vertex_t new_vert();
extern edge_t new_edge();
extern face_t new_face();
extern face_t new_tetrahedron();
extern int vert_pr();
extern int edge_pr();
extern int face_pr();
extern int short_vert_pr();
extern int short_edge_pr();
extern int short_face_pr();
extern int short_poly_pr();
extern face_t read_face();
extern edge_t read_edge();
extern vertex_t read_vertex();
extern void face_to_file();
extern void choose_struct();

extern void F_to_array();
extern int E_to_array();
extern int V_to_array();
extern void read_make_vertex();
extern face_t file_make_structs();
extern void read_make_edge();
extern  void read_make_face();
extern vertex_t findv();
extern edge_t finde();
extern face_t findf();

extern  void p_face_to_file();
extern  void p_edge_to_file();
extern void file_link_structs();
extern  void p_vert_to_file();

extern  face_t fake_face();
extern  void face_to_file();

extern   face_t new_tetrahedron();


#endif
