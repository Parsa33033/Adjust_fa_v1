import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-tokens.reducer';
import { IAdjustTokens } from 'app/shared/model/adjust-tokens.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustTokensProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustTokens = (props: IAdjustTokensProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustTokensList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-tokens-heading">
        <Translate contentKey="adjustApp.adjustTokens.home.title">Adjust Tokens</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustTokens.home.createLabel">Create new Adjust Tokens</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustTokensList && adjustTokensList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustTokens.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustTokens.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustTokens.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustTokens.price">Price</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustTokens.image">Image</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustTokensList.map((adjustTokens, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustTokens.id}`} color="link" size="sm">
                      {adjustTokens.id}
                    </Button>
                  </td>
                  <td>{adjustTokens.name}</td>
                  <td>{adjustTokens.description}</td>
                  <td>{adjustTokens.token}</td>
                  <td>{adjustTokens.price}</td>
                  <td>
                    {adjustTokens.image ? (
                      <div>
                        {adjustTokens.imageContentType ? (
                          <a onClick={openFile(adjustTokens.imageContentType, adjustTokens.image)}>
                            <img src={`data:${adjustTokens.imageContentType};base64,${adjustTokens.image}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {adjustTokens.imageContentType}, {byteSize(adjustTokens.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustTokens.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustTokens.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustTokens.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.adjustTokens.home.notFound">No Adjust Tokens found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustTokens }: IRootState) => ({
  adjustTokensList: adjustTokens.entities,
  loading: adjustTokens.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTokens);
