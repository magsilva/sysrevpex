#include "my_lssolve.h"

int convert(cs *A, float *x, int nRows, int column)
{
  int n, p, *Ap, *Ai;
  float *Ax ;
  
  n=A->n;
  
  if(column > n) return 1;
  
  Ap = A->p; 
  Ai = A->i; 
  Ax = A->x;
  
  for(p=0; p < nRows; p++)
  {
    x[p]=0;
  }
  
  for(p = Ap[column]; p < Ap[column+1]; p++)
  {
    x[Ai[p]]= Ax ? Ax[p] : 1;
  }
  
  return 0;
}

void lssolve(cs *A, cs *B, float *X, float *Y)
{
  cs *At, *AtA, *AtB;
  css *S = NULL;
  csn *N = NULL;
  float *x, *y, *b;
  int n, m, j;   
  
  At=cs_transpose(A, 1);
  AtA=cs_multiply(At, A);
  AtB=cs_multiply(At, B);
  
  n=AtA->n;
  m=AtB->n;
  
  x = (float*)cs_malloc(n, sizeof (float));
  y = (float*)cs_malloc(n, sizeof (float));
  b = (float*)cs_malloc(n, sizeof (float));
  
  if(x==NULL || y==NULL || b==NULL) 
  {
     exit(0);
  }  
  
  S = cs_schol (1, AtA) ;		    /* ordering and symbolic analysis */
  N = cs_chol (AtA, S) ;		    /* numeric Cholesky factorization */
    
  convert(AtB, b, n, 0);
  
  cs_ipvec(S->pinv, b, y, n) ;	/* y = P*b */
  cs_lsolve(N->L, y) ;			/* y = L\y */
  cs_ltsolve(N->L, y) ;			/* y = L'\y */
  cs_pvec(S->pinv, y, x, n) ;		/* x = P'*y */
  
  for(j=0; j < n; j++)
  {
    X[j]=*(x+j);
  }
  
  convert(AtB, b, n, 1);
  
  cs_ipvec(S->pinv, b, y, n) ;	/* y = P*b */
  cs_lsolve(N->L, y) ;			/* y = L\y */
  cs_ltsolve(N->L, y) ;			/* y = L'\y */
  cs_pvec(S->pinv, y, x, n) ;		/* x = P'*y */
  
  for(j=0; j < n; j++)
  {
    Y[j]=*(x+j);
  } 
  
  cs_free(y);
  cs_free(b);
  cs_free(x);
  
  cs_sfree(S);
  cs_nfree(N);
  
  cs_spfree(At);
  cs_spfree(AtA);
  cs_spfree(AtB);
}
